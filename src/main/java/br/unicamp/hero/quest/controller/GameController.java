package br.unicamp.hero.quest.controller;

import br.unicamp.hero.quest.constant.*;
import br.unicamp.hero.quest.exception.*;
import br.unicamp.hero.quest.factory.board.*;
import br.unicamp.hero.quest.model.board.*;
import br.unicamp.hero.quest.model.characters.Character;
import br.unicamp.hero.quest.model.characters.hero.*;
import br.unicamp.hero.quest.service.*;
import br.unicamp.hero.quest.service.input.*;
import br.unicamp.hero.quest.service.render.*;
import br.unicamp.hero.quest.utils.*;

import static br.unicamp.hero.quest.constant.InterfaceText.QUIT_COMMAND;
import static br.unicamp.hero.quest.constant.InterfaceText.UNKNOWN_ACTION_MESSAGE;

public class GameController {
    private final Board board;

    private final InputService inputService;
    private final RenderService renderService;
    private final ActionService actionService;
    private final ScavengeService scavengeService;

    MovementController movementController;

    public GameController(
        Hero hero,
        BoardFactory boardFactory,
        InputService inputService,
        RenderService renderService
    ) {
        this.board = boardFactory.getBoard();
        this.board.addCharacter(hero);

        this.inputService = inputService;
        this.renderService = renderService;

        this.actionService = new ActionService(board);
        this.scavengeService = new ScavengeService(board);
        this.movementController = new MovementController(new MovementService(board));

        renderService.render(board);
    }

    public void manageRound(Character character) {
        if (character instanceof Hero) {
            managePlayerRound(character);
        } else {
            manageMobRound(character);
        }
    }

    private void manageMobRound(Character character) {
        movementController.startWalkPhase(character);

        try {
            movementController.walk(character, PositionUtils.randomDirection());
        } catch (Exception ignored) {
        }

        movementController.endWalkPhase(character);
    }

    private void managePlayerRound(Character character) {
        Command command = inputService.readCommand();

        if (command.isWalkCommand()) {
            walkPhase(character);
            actionPhase(character);
        } else {
            actionPhase(character);
            command = inputService.readCommand();
            if (command.isWalkCommand()) {
                walkPhase(character);
            }
        }
    }

    private void actionPhase(Character character) {
        boolean validAction = false;

        while (!validAction) {
            displayInformation("Executing " + inputService.getLastCommand().name());
            switch (inputService.getLastCommand()) {
                case SCAVENGE:
                    scavengeService.pickStuff(character);
                    validAction = true;
                    break;
                case CAST_SPELL:
                case ATTACK:
                    actionService.doStuff(character, inputService.getLastCommand());
                    validAction = true;
                    break;
                case QUIT:
                    return;
                default:
                    renderService.displayMessage(String.format(UNKNOWN_ACTION_MESSAGE, QUIT_COMMAND));
                    inputService.readCommand();
            }
        }
    }

    private void walkPhase(Character character) {
        Command command = inputService.getLastCommand();

        movementController.startWalkPhase(character);
        while (command.isWalkCommand()) {
            try {
                movementController.walk(character, directionFromCommand(command));
            } catch (MoveException e) {
                renderService.displayMessage(e.getMessage());
            }
            displayInformation(String.format("Remaining steps: %d", movementController.remainingSteps(character)));
            command = inputService.readCommand();
        }
    }

    private void managePlayerRoundOld(Character character) {
        Command command = inputService.readCommand();

        movementController.startWalkPhase(character);

        boolean acted = false;

        while (command != Command.QUIT) {
            switch (command) {
                case MOVE_DOWN:
                case MOVE_UP:
                case MOVE_LEFT:
                case MOVE_RIGHT:
                    try {
                        movementController.walk(character, directionFromCommand(command));
                    } catch (MoveException e) {
                        renderService.displayMessage(e.getMessage());
                    }
                    break;

                case SCAVENGE:
                    if (!acted) {
                        scavengeService.pickStuff(character);
                    }
                    acted = true;
                    break;

                case CAST_SPELL:
                case ATTACK:
                    if (!acted) {
                        actionService.doStuff(character, command);
                    }
                    acted = true;
                    break;

                default:
                    renderService.displayMessage("Command not available");
            }
            displayInformation(String.format("Remaining steps: %d", movementController.remainingSteps(character)));
            command = inputService.readCommand();
        }
        movementController.endWalkPhase(character);
    }

    private Direction directionFromCommand(Command command) {
        switch (command) {
            case MOVE_UP:
                return Direction.UP;
            case MOVE_DOWN:
                return Direction.DOWN;
            case MOVE_RIGHT:
                return Direction.RIGHT;
            case MOVE_LEFT:
                return Direction.LEFT;
            default:
                return null;
        }
    }

    private void displayInformation(String message) {
        renderService.displayMessage(message);
        renderService.render(board);
    }
}
