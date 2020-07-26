package br.unicamp.hero.quest.controller;

import br.unicamp.hero.quest.constant.*;
import static br.unicamp.hero.quest.constant.Command.*;
import static br.unicamp.hero.quest.constant.InterfaceText.*;
import br.unicamp.hero.quest.exception.*;
import br.unicamp.hero.quest.factory.board.*;
import br.unicamp.hero.quest.model.board.*;
import br.unicamp.hero.quest.model.characters.Character;
import br.unicamp.hero.quest.model.characters.enemy.*;
import br.unicamp.hero.quest.model.characters.hero.*;
import br.unicamp.hero.quest.service.*;
import br.unicamp.hero.quest.service.action.*;
import br.unicamp.hero.quest.service.input.*;
import br.unicamp.hero.quest.service.render.*;
import br.unicamp.hero.quest.utils.*;

import java.util.*;

public class GameController {
    private final Board board;

    private final InputService inputService;
    private final RenderService renderService;
    private final ScavengeService scavengeService;

    private final HeroActionService heroActionService;
    private final EnemyActionService enemyActionService;

    private final MovementController movementController;

    public GameController(
        BoardFactory boardFactory,
        InputService inputService,
        RenderService renderService
    ) {
        this.board = boardFactory.getBoard();
        ActionFactories.createInstance(this.board);

        this.inputService = inputService;
        this.renderService = renderService;

        this.heroActionService = new HeroActionService(board, inputService);
        this.enemyActionService = new EnemyActionService(board);

        this.scavengeService = new ScavengeService(board);
        this.movementController = new MovementController(new MovementService(board));
    }

    public void startGame() {
        final List<Character> characters = this.board.getCharacters();
        if (characters.isEmpty()) {
            renderService.displayMessage(NO_CHARACTERS_MESSAGE);
            return;
        }

        int roundNumber = 0;
        renderService.render(this.board);
        do {
            roundNumber++;
            renderService.displayMessage(" ".repeat(31) + String.format(ROUND_BANNER_MESSAGE, roundNumber));

            for (Character character : characters) {
                renderService.displayMessage(String.format(CHARACTERS_TURN_MESSAGE, character.getName()));
                manageRound(character);
            }

            renderService.displayMessage(CONTINUES_CONFIRMATION_MESSAGE);
        } while (inputService.readCommand() != QUIT);
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
                }
            }
        } catch (Exception ignored) {
        }

        try {
            Command command = getRandomAction(character);
            switch (command) {
                case ATTACK:
                    this.enemyActionService.useWeapon(character);
                    break;

                case CAST_SPELL:
                    this.enemyActionService.castSpell(character);
                    break;
            }
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
            return ATTACK;
        } else if (character instanceof Skeleton) {
            return ATTACK;
        } else if (character instanceof SkeletonMage) {
            return List.of(ATTACK, CAST_SPELL).get(new Random().nextInt(2));
        } else {
            return List.of(ATTACK, CAST_SPELL, SCAVENGE).get(new Random().nextInt(3));
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
                    scavengeService.scavenge(character);
                    validAction = true;
                    break;
                case CAST_SPELL:
                    this.heroActionService.castSpell(character);
                    validAction = true;
                    break;
                case ATTACK:
                    this.heroActionService.useWeapon(character);
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
        this.board.addCharacter(characterToAdd);
    }

    public void addCharacters(Character... charactersToAdd) {
        for (Character character : charactersToAdd) {
            addCharacter(character);
        }
    }
}
