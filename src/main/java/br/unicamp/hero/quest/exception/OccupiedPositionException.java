package br.unicamp.hero.quest.exception;

public class OccupiedPositionException extends MoveException {
    public OccupiedPositionException(String message) {
        super(message);
    }
}
