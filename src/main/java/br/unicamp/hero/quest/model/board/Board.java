package br.unicamp.hero.quest.model.board;

import br.unicamp.hero.quest.model.actions.weapon.*;

import java.util.*;
import java.util.stream.*;

public class Board {
    private final List<List<TileType>> map;

    public Board() {
        this.map = List.of(
            List.of(TileType.WALL, TileType.WALL, TileType.WALL, TileType.WALL, TileType.WALL),
            List.of(TileType.WALL, TileType.PATH, TileType.PATH, TileType.PATH, TileType.WALL),
            List.of(TileType.WALL, TileType.PATH, TileType.WALL, TileType.PATH, TileType.WALL),
            List.of(TileType.WALL, TileType.PATH, TileType.WALL, TileType.PATH, TileType.WALL),
            List.of(TileType.WALL, TileType.PATH, TileType.PATH, TileType.PATH, TileType.WALL),
            List.of(TileType.WALL, TileType.WALL, TileType.WALL, TileType.WALL, TileType.WALL)
        );
    }

    @Override
    public String toString() {
        return this.map.stream()
            .map(it ->
                it.stream()
                    .map(cell -> cell == TileType.WALL ? "XX" : "  ")
                    .collect(Collectors.joining())
            )
            .collect(Collectors.joining("\n"));
    }
}
