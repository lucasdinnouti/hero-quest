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
        this.board = boardFactory.getBoard(hero);

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

    private void managePlayerRoundNew(Character character) {
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
        switch (inputService.getLastCommand()) {
            case SCAVENGE:
                scavengeService.pickStuff(character);
                break;
            case CAST_SPELL:
            case ATTACK:
                actionService.doStuff(character, inputService.getLastCommand());
                break;
            case END_ROUND:
                return;
            default:
                throw new InvalidCommandException();
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

    private void managePlayerRound(Character character) {
        Command command = inputService.readCommand();

        movementController.startWalkPhase(character);

        boolean acted = false;

        while (command != Command.END_ROUND) {
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
                    throw new InvalidCommandException();
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
