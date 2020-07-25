package br.unicamp.hero.quest.service.input;

import br.unicamp.hero.quest.constant.Command;
import br.unicamp.hero.quest.constant.InterfaceText;
import br.unicamp.hero.quest.service.input.*;

import java.io.InputStream;
import java.util.Scanner;

public class KeyboardInputService implements InputService {

    private final Scanner scanner;

    private Command lastCommand;

    public KeyboardInputService(InputStream in) {
        this.scanner = new Scanner(in);
    }

    @Override
    public Command readCommand() {
        String commandText = scanner.nextLine();

        switch (commandText) {
            case InterfaceText.ATTACK_COMMAND:
                lastCommand = Command.ATTACK;
                break;
            case InterfaceText.MOVE_DOWN_COMMAND:
                lastCommand = Command.MOVE_DOWN;
                break;
            case InterfaceText.MOVE_UP_COMMAND:
                lastCommand = Command.MOVE_UP;
                break;
            case InterfaceText.MOVE_LEFT_COMMAND:
                lastCommand = Command.MOVE_LEFT;
                break;
            case InterfaceText.MOVE_RIGHT_COMMAND:
                lastCommand = Command.MOVE_RIGHT;
                break;
            case InterfaceText.SCAVENGE_COMMAND:
                lastCommand = Command.SCAVENGE;
                break;
            case InterfaceText.CAST_SPELL_COMMAND:
                lastCommand = Command.CAST_SPELL;
                break;
            case InterfaceText.QUIT_COMMAND:
                lastCommand = Command.QUIT;
                break;
            default:
                lastCommand =  Command.INVALID;
        }

        return lastCommand;
    }

    @Override
    public Command getLastCommand() {
        return lastCommand;
    }
}












































