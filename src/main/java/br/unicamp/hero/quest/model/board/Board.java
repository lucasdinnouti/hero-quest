package br.unicamp.hero.quest.model.board;

import br.unicamp.hero.quest.model.*;
import br.unicamp.hero.quest.model.characters.hero.*;

import java.util.*;
import java.util.stream.*;

public class Board {
    private final List<Point> edges;
    private final List<TileType> map;
    private final Hero hero;

    private final int sizeX;
    private final int sizeY;

    public Board(Hero hero, int sizeX, int sizeY, List<TileType> map) {
        this.sizeY = sizeY;
        this.sizeX = sizeX;
        this.map = map;
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

    public TileType getTile(Point point) {
        return this.getTile(point.getX(), point.getY());
    }

    public TileType getTile(int x, int y) {
        return this.map.get(y * this.sizeX + x);
    }

    public boolean isWall(Point point) {
        return this.getTile(point) == TileType.WALL;
    }

    public List<Point> getEdges() {
        return edges;
    }

    public Hero getHero() {
        return hero;
    }

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }
}
