package br.unicamp.hero.quest.model.board;

import br.unicamp.hero.quest.model.*;
import br.unicamp.hero.quest.model.characters.hero.*;

import java.util.*;
import java.util.stream.*;

public class Board {
    private final List<Point> edges;
    private final List<List<TileType>> map;
    private final Hero hero;

    private final int sizeX;
    private final int sizeY;

    public Board(Hero hero) {
        this.map = List.of(
            List.of(TileType.WALL, TileType.WALL, TileType.WALL, TileType.WALL, TileType.WALL),
            List.of(TileType.WALL, TileType.PATH, TileType.PATH, TileType.PATH, TileType.WALL),
            List.of(TileType.WALL, TileType.PATH, TileType.WALL, TileType.PATH, TileType.WALL),
            List.of(TileType.WALL, TileType.PATH, TileType.WALL, TileType.PATH, TileType.WALL),
            List.of(TileType.WALL, TileType.PATH, TileType.PATH, TileType.PATH, TileType.WALL),
            List.of(TileType.WALL, TileType.WALL, TileType.WALL, TileType.WALL, TileType.WALL)
        );

        this.sizeY = this.map.size();
        this.sizeX = this.map.get(0).size();
        this.hero = hero;

        this.edges = IntStream.range(0, sizeX)
            .mapToObj(x -> List.of(new Point(x, 0), new Point(x, sizeY - 1)))
            .flatMap(Collection::stream)
            .collect(Collectors.toList());

        this.edges.addAll(
            IntStream.range(1, sizeY - 1)
                .mapToObj(y -> List.of(new Point(0, y), new Point(sizeX - 1, y)))
                .flatMap(Collection::stream)
                .collect(Collectors.toList())
        );
    }

    private List<Point> bresenhamLine(Point end) {
        final Point start = this.hero.getPosition();

        int x1 = start.getX();
        int y1 = start.getY();
        int x2 = end.getX();
        int y2 = end.getY();
        final boolean isSteep = Math.abs(y2 - y1) > Math.abs(x2 - x1);

        if (isSteep) {
            x1 = start.getY();
            y1 = start.getX();
            x2 = end.getY();
            y2 = end.getX();
        }

        final boolean isReversed = x1 > x2;
        if (isReversed) {
            int oldX1 = x1;
            x1 = x2;
            x2 = oldX1;

            int oldY1 = y1;
            y1 = y2;
            y2 = oldY1;
        }

        int dx = x2 - x1;
        int dy = Math.abs(y2 - y1);

        int error = (int)(dx / 2.0);
        final int yStep = y1 < y2 ? 1 : -1;

        final List<Point> points = new ArrayList<>();
        for (int x = x1, y = y1; x < x2 + 1; x++) {
            final Point coord = isSteep ? new Point(y, x) : new Point(x, y);
            points.add(coord);

            error -= dy;
            if (error < 0) {
                y += yStep;
                error += dx;
            }
        }

        if (isReversed) {
            Collections.reverse(points);
        }

        return points;
    }

    @Override
    public String toString() {
        final List<Point> visible = this.edges.stream()
            .map(this::bresenhamLine)
            .flatMap(ray -> ray.stream().takeWhile(p -> this.map.get(p.getY()).get(p.getX()) != TileType.WALL))
            .collect(Collectors.toList());

        return IntStream.range(0, this.sizeY)
            .mapToObj(y ->
                IntStream.range(0, this.sizeX)
                    .mapToObj(x -> {
                        if (visible.stream().anyMatch(point -> point.getY() == y && point.getX() == x)) {
                            return "  ";
                        }

                        final TileType tile = this.map.get(y).get(x);
                        if (tile == TileType.PATH) {
                            return "::";
                        }

                        final Region region = Region.fromHero(x, y, this.hero);
                        final boolean isVisible = visible.stream()
                            .anyMatch(point ->
                                point.getY() == y + region.yDiff && point.getX() == x
                                    || point.getY() == y && point.getX() == x + region.xDiff
                                    || point.getY() == y + region.yDiff && point.getX() == x + region.xDiff
                            );

                        if (isVisible) {
                            return "XX";
                        }

                        return "::";
                    })
                    .collect(Collectors.joining())
            )
            .collect(Collectors.joining("\n"));
    }

    private enum Region {
        NW(1, 1),
        NE(-1, 1),
        SW(1, -1),
        SE(-1, -1);

        final int xDiff, yDiff;
        Region(int xDiff, int yDiff) {
            this.xDiff = xDiff;
            this.yDiff = yDiff;
        }

        public static Region fromHero(int x, int y, Hero hero) {
            final Point heroPosition = hero.getPosition();
            if (y <= heroPosition.getY()) {
                return x <= heroPosition.getX() ? NW : NE;
            } else {
                return x <= heroPosition.getX() ? SW : SE;
            }
        }
    }
}
