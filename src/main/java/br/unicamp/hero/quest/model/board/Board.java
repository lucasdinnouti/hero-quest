package br.unicamp.hero.quest.model.board;

import br.unicamp.hero.quest.model.*;
import br.unicamp.hero.quest.model.characters.Character;
import br.unicamp.hero.quest.model.characters.hero.*;

import java.util.*;
import java.util.stream.*;

public class Board {
    private final List<Point> edges;
    private final List<TileType> map;
    private final List<Character> characters;

    private final int sizeX;
    private final int sizeY;

    public Board(int sizeX, int sizeY, List<TileType> map) {
        this.sizeY = sizeY;
        this.sizeX = sizeX;
        this.map = map;
        this.characters = new ArrayList<>();

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

    public void addCharacter(Character character) {
        this.characters.add(character);
    }

    public Optional<Character> getCharacter(Point point) {
        return this.getCharacter(point.getX(), point.getY());
    }

    public Optional<Character> getCharacter(int x, int y) {
        return this.characters.stream()
            .filter(it -> it.getPosition().equals(x, y))
            .findFirst();
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

    public boolean isInsideBoard(Point point) {
        return point.getX() > 0 && point.getY() > 0 && point.getX() < sizeX && point.getY() < sizeY;
    }

    public List<Point> getEdges() {
        return edges;
    }

    public Hero getHero() {
        return (Hero) this.characters.stream()
            .filter(it -> it instanceof Hero)
            .findFirst()
            .get();
    }

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }
}
