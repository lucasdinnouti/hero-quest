package br.unicamp.hero.quest.controller;

import br.unicamp.hero.quest.constant.*;
import static br.unicamp.hero.quest.constant.InterfaceText.*;
import br.unicamp.hero.quest.exception.*;
import br.unicamp.hero.quest.factory.board.*;
import br.unicamp.hero.quest.model.board.*;
import br.unicamp.hero.quest.model.characters.Character;
import br.unicamp.hero.quest.model.characters.enemy.*;
import br.unicamp.hero.quest.model.characters.hero.*;
import br.unicamp.hero.quest.service.*;
import br.unicamp.hero.quest.service.input.*;
import br.unicamp.hero.quest.service.render.*;
import br.unicamp.hero.quest.utils.*;

import java.util.*;

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

        this.actionService = new ActionService(board, inputService);
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
        } catch (Exception ignored) {
        }

        try {
            // TODO: Implement mob attack
            getRandomAction(character);
        } catch (Exception ignored) {
        }

        movementController.endWalkPhase(character);
    }

    private ArrayList<Direction> shuffledDirections() {
        ArrayList<Direction> shuffledDirections = new ArrayList<>(PositionUtils.allDirections);
        Collections.shuffle(shuffledDirections);

        return shuffledDirections;
    }

    private Command getRandomAction(Character character) {
        if (character instanceof Goblin) {
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

        renderService.displayMessage(ACTION_PHASE_MESSAGE);
        while (!validAction) {
            displayInformation("Executing " + inputService.getLastCommand().name());
            switch (inputService.getLastCommand()) {
                case SCAVENGE:
                    scavengeService.pickStuff(character);
                    validAction = true;
                    break;
                case CAST_SPELL:
                    this.actionService.castSpell(character);
                    validAction = true;
                    break;
                case ATTACK:
                    this.actionService.useWeapon(character);
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


    /**
     * Note that, for an invalid command what is going to happen is that walkPhase will be ended.
     * This may seem like a mistake if you're trying to move and misses a command, but fits in the
     * behavior designed which is: for a command which is not walking, an action should be tried.
     */
    private void walkPhase(Character character) {
        Command command = inputService.getLastCommand();

        renderService.displayMessage(WALK_PHASE_MESSAGE);
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

    public void addCharacter(Character characterToAdd) {
        characters.add(characterToAdd);
    }
    public void addCharacters(Character ... charactersToAdd) {
        for (Character character : charactersToAdd) {
            addCharacter(character);
        }
    }
}
