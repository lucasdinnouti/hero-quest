package br.unicamp.hero.quest.model.board;

public enum TileType {
    WALL("XX"),
    PATH("  "),
    SHADOW("::"),
    EMPTY("  ");

    private final String representation;

    TileType(String representation) {
        this.representation = representation;
    }

    public String getRepresentation() {
        return representation;
    }
}
