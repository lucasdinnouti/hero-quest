package br.unicamp.hero.quest.controller;

import br.unicamp.hero.quest.constant.Direction;
import br.unicamp.hero.quest.exception.LackOfStepsException;
import br.unicamp.hero.quest.model.characters.Character;
import br.unicamp.hero.quest.service.MovementService;
import java.util.HashMap;

import static br.unicamp.hero.quest.constant.InterfaceText.LACK_OF_STEPS_MESSAGE;
import static br.unicamp.hero.quest.service.DiceService.rollDice;

public class MovementController {
    MovementService movementService;

    HashMap<Character, Integer> stepsByCharacter;

    public MovementController(MovementService movementService) {
        this.movementService = movementService;
        stepsByCharacter = new HashMap<>();
    }

    public void walk(Character character, Direction direction) {
        int stepsRemaining = stepsByCharacter.getOrDefault(character,  -1);

        if (stepsRemaining > 0) {
            movementService.moveCharacter(character, direction);
        } else {
            throw new LackOfStepsException(String.format(LACK_OF_STEPS_MESSAGE, character.toString()));
        }
    }

    public void startWalkPhase(Character character) {
        stepsByCharacter.put(character, rollDice(2, 6));
    }

    public void endWalkPhase(Character character) {
        stepsByCharacter.put(character, null);
    }
}
