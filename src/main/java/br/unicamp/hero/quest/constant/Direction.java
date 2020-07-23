package br.unicamp.hero.quest.constant;

public enum Direction {
    UP(0, -1),
    LEFT(-1, 0),
    DOWN(1, 0),
    RIGHT(0, 1);

    private int x;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    private int y;

    Direction(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
