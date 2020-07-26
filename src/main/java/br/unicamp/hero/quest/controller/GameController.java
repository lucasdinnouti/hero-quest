package br.unicamp.hero.quest.controller;

import br.unicamp.hero.quest.constant.Command;
import br.unicamp.hero.quest.constant.Direction;
import br.unicamp.hero.quest.exception.MoveException;
import br.unicamp.hero.quest.factory.board.BoardFactory;
import br.unicamp.hero.quest.model.board.Board;
import br.unicamp.hero.quest.model.characters.Character;
import br.unicamp.hero.quest.model.characters.enemy.Goblin;
import br.unicamp.hero.quest.model.characters.enemy.Skeleton;
import br.unicamp.hero.quest.model.characters.enemy.SkeletonMage;
import br.unicamp.hero.quest.model.characters.hero.Hero;
import br.unicamp.hero.quest.service.ActionService;
import br.unicamp.hero.quest.service.MovementService;
import br.unicamp.hero.quest.service.ScavengeService;
import br.unicamp.hero.quest.service.input.InputService;
import br.unicamp.hero.quest.service.render.RenderService;
import br.unicamp.hero.quest.utils.PositionUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Random;

import static br.unicamp.hero.quest.constant.InterfaceText.*;

public class GameController {
    private final Board board;

    private final InputService inputService;
    private final RenderService renderService;
    private final ActionService actionService;
    private final ScavengeService scavengeService;

    ArrayList<Character> characters;

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

        characters = new ArrayList<>();

        renderService.render(board);
    }

    public void startGame() {

        if (characters.isEmpty()) {
            renderService.displayMessage(NO_CHARACTERS_MESSAGE);
            return;
        }

        int roundNumber = 0;

        do {
            roundNumber++;
            renderService.displayMessage(" ".repeat(31) + String.format(ROUND_BANNER_MESSAGE, roundNumber));

            for (Character character : characters) {
                renderService.displayMessage(String.format(CHARACTERS_TURN_MESSAGE, character.getName()));
                manageRound(character);
            }

            renderService.displayMessage(CONTINUES_CONFIRMATION_MESSAGE);
        } while (inputService.readCommand() != Command.QUIT);
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


            List<Direction> directions = new ArrayList<>(
                character instanceof Goblin ?
                PositionUtils.directionsToPoint(character.getPosition(), board.getHero().getPosition()) :
                shuffledDirections()
            );

            boolean couldMove = false;
            while (!couldMove && directions.size() > 0) {
                try {
                    movementController.walk(character, PositionUtils.randomDirection());
                    couldMove = true;
                } catch (MoveException e) {
                    directions.remove(0);
                    renderService.displayMessage(e.getMessage());
                }
            }
        } catch (Exception ignored) { }

        try {
            actionService.doStuff(character, getRandomAction(character));
        } catch (Exception ignored) { }

        movementController.endWalkPhase(character);
    }

    private ArrayList<Direction> shuffledDirections() {
        ArrayList<Direction> shuffledDirections = new ArrayList<>(PositionUtils.allDirections);
        Collections.shuffle(shuffledDirections);

        return shuffledDirections;
    }

    private Command getRandomAction(Character character) {
        if (character instanceof Goblin ) {
            return Command.ATTACK;
        } else if (character instanceof Skeleton) {
            return Command.ATTACK;
        } else if (character instanceof SkeletonMage) {
            return List.of(Command.ATTACK, Command.CAST_SPELL).get(new Random().nextInt(2));
        } else {
            return List.of(Command.ATTACK, Command.CAST_SPELL, Command.SCAVENGE).get(new Random().nextInt(3));
        }
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

    public void addCharacter(Character character) {
        characters.add(character);
    }
}
