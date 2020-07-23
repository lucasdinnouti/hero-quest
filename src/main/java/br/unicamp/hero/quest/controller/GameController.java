package br.unicamp.hero.quest.controller;

import br.unicamp.hero.quest.constant.Command;
import br.unicamp.hero.quest.constant.Direction;
import br.unicamp.hero.quest.exception.LackOfStepsException;
import br.unicamp.hero.quest.exception.MoveException;
import br.unicamp.hero.quest.exception.OccupiedPositionException;
import br.unicamp.hero.quest.model.characters.Character;
import br.unicamp.hero.quest.service.ActionService;
import br.unicamp.hero.quest.service.KeyboardService;
import br.unicamp.hero.quest.service.ScavengeService;

public class GameController {
    KeyboardService keyboardService;
    ActionService actionService;
    ScavengeService scavengeService;
    MovementController movementController;

    public GameController(KeyboardService keyboardService, MovementController movementController) {
        this.keyboardService = keyboardService;
        this.movementController = movementController;
    }

    public void manageRound(Character character) {

        Command command = keyboardService.readCommand();

        if (command.isWalkCommand()) {
            movementController.startWalk(character);
            while (command.isWalkCommand()) {
                try {
                    movementController.walk(character, extractDirection(command));
                } catch (MoveException e) {
                    System.out.println(String.format("MoveException. %s", e.toString()));
                }

                command = keyboardService.readCommand();
            }
        }

        if (command == Command.SCAVENGE) {
            scavengeService.pickStuff(character);
        } else {
            actionService.doStuff(character, command);
        }



    }

    private Direction extractDirection(Command command) {
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
