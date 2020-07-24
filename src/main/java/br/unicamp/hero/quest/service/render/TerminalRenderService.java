package br.unicamp.hero.quest.service.render;

import br.unicamp.hero.quest.model.*;
import br.unicamp.hero.quest.model.board.*;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class TerminalRenderService implements RenderService {
    @Override
    public void render(Board board) {
        final Point heroPosition = board.getHero().getPosition();
        final Function<Point, List<Point>> bresenhanCurry = this.bresenhamLine(heroPosition);

        final List<Point> visible = board.getEdges().stream()
            .map(bresenhanCurry)
            .flatMap(ray -> ray.stream().takeWhile(point -> !board.isWall(point)))
            .collect(Collectors.toList());

        final String map = IntStream.range(0, board.getSizeY()).mapToObj(y ->
            IntStream.range(0, board.getSizeX()).mapToObj(x -> {
                if (visible.stream().anyMatch(point -> point.getY() == y && point.getX() == x)) {
                    return "  ";
                }

                final TileType tile = board.getTile(x, y);
                if (tile == TileType.PATH) {
                    return "::";
                }

                final Region region = Region.fromCenter(x, y, heroPosition);
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
            }).collect(Collectors.joining())
        ).collect(Collectors.joining("\n"));

        System.out.println(map);
    }

    private Function<Point, List<Point>> bresenhamLine(Point start) {
        return (end) -> {
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

            int error = (int) (dx / 2.0);
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
        };
    }
}
