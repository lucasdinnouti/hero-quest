package br.unicamp.hero.quest.exception;

public class InvalidCommandException extends IllegalArgumentException {
    public InvalidCommandException() {
        super("Command not found");
    }
}
