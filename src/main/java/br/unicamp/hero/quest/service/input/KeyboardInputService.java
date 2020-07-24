package br.unicamp.hero.quest.service.input;

import br.unicamp.hero.quest.constant.Command;
import br.unicamp.hero.quest.constant.InterfaceText;
import br.unicamp.hero.quest.service.input.*;

import java.io.InputStream;
import java.util.Scanner;

public class KeyboardInputService implements InputService {

    private final Scanner scanner;

    public KeyboardInputService(InputStream in) {
        this.scanner = new Scanner(in);
    }

    public Command readCommand() {
        String commandText = scanner.nextLine();

        switch (commandText) {
            case InterfaceText.ATTACK_COMMAND:
                return Command.ATTACK;
            case InterfaceText.MOVE_DOWN_COMMAND:
                return Command.MOVE_DOWN;
            case InterfaceText.MOVE_UP_COMMAND:
                return Command.MOVE_UP;
            case InterfaceText.MOVE_LEFT_COMMAND:
                return Command.MOVE_LEFT;
            case InterfaceText.MOVE_RIGHT_COMMAND:
                return Command.MOVE_RIGHT;
            case InterfaceText.SCAVENGE_COMMAND:
                return Command.SCAVENGE;
            case InterfaceText.CAST_SPELL_COMMAND:
                return Command.CAST_SPELL;
            default:
                return Command.INVALID;
        }
    }
}