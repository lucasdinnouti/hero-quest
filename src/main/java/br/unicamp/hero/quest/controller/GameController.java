package br.unicamp.hero.quest.controller;

import br.unicamp.hero.quest.constant.*;
import br.unicamp.hero.quest.factory.board.*;
import br.unicamp.hero.quest.model.board.*;
import br.unicamp.hero.quest.model.characters.Character;
import br.unicamp.hero.quest.model.characters.hero.*;
import br.unicamp.hero.quest.service.*;
import br.unicamp.hero.quest.service.render.*;
import br.unicamp.hero.quest.utils.*;

public class GameController {
    Board board;

    InputService inputService;
    RenderService renderService;
    ActionService actionService;
    ScavengeService scavengeService;

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
                    movementController.walk(character, directionFromCommand(command));
                    break;
                case SCAVENGE:
                    if (!acted) {
                        scavengeService.pickStuff(character);
                    }
                    acted = true;
                    break;
                default:
                    if (!acted) {
                        actionService.doStuff(character, command);
                    }
                    acted = true;
                    break;
            }
            renderService.render(board);
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
}
