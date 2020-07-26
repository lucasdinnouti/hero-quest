package br.unicamp.hero.quest.constant;

public class InterfaceText {

    public static final String MOVE_UP_COMMAND = "w";
    public static final String MOVE_LEFT_COMMAND = "a";
    public static final String MOVE_DOWN_COMMAND = "s";
    public static final String MOVE_RIGHT_COMMAND = "d";

    public static final String ATTACK_COMMAND = "j";
    public static final String SCAVENGE_COMMAND = "k";
    public static final String CAST_SPELL_COMMAND = "l";

    public static final String QUIT_COMMAND = "q";

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    public static final String LACK_OF_STEPS_MESSAGE = ANSI_RED + "Character %s can not move, as their steps ran over." + ANSI_RESET;
    public static final String INVALID_POSITION_MESSAGE = ANSI_RED + "Character %s can not move, as position %s is invalid." + ANSI_RESET;
    public static final String UNKNOWN_ACTION_MESSAGE = ANSI_RED + "No action associated, may want to quit (%s)" + ANSI_RESET;
    public static final String NO_CHARACTERS_MESSAGE = ANSI_RED + "No characters added, cant run the game." + ANSI_RESET;

    public static final String ROUND_BANNER_MESSAGE = ANSI_GREEN + "ROUND %d" + ANSI_RESET;
    public static final String CHARACTERS_TURN_MESSAGE = ANSI_GREEN + "%s's turn." + ANSI_RESET;
    public static final String CONTINUES_CONFIRMATION_MESSAGE = ANSI_GREEN + "Round over, want to quit? (q)" + ANSI_RESET;

    public static final String WALK_PHASE_MESSAGE = ANSI_BLUE + "WALKING " + ANSI_RESET;
    public static final String ACTION_PHASE_MESSAGE = ANSI_BLUE + "EXECUTING ACTION" + ANSI_RESET;

}
