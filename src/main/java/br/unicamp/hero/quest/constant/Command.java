package br.unicamp.hero.quest.constant;

public enum Command {
    MOVE_DOWN(true),
    MOVE_UP(true),
    MOVE_LEFT(true),
    MOVE_RIGHT(true),

    ATTACK(false),
    SCAVENGE(false),
    CAST_SPELL(false),

    QUIT(false),

    INVALID(false);

    private final boolean walkCommand;

    Command(boolean walkCommand) {
        this.walkCommand = walkCommand;
    }

    public boolean isWalkCommand() {
        return walkCommand;
    }
}
