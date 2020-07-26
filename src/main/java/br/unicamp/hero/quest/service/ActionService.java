package br.unicamp.hero.quest.service;

import br.unicamp.hero.quest.model.actions.spell.defense.*;
import br.unicamp.hero.quest.model.board.*;
import br.unicamp.hero.quest.model.characters.Character;
import br.unicamp.hero.quest.service.input.*;
import br.unicamp.hero.quest.utils.*;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class ActionService {
    private final Board board;
    private final InputService inputService;

    public ActionService(Board board, InputService inputService) {
        this.board = board;
        this.inputService = inputService;
    }

    public void castSpell(Character character) {
        character.getSpellAction().ifPresent(spell -> {
            if (spell instanceof DefenseSpell) {
                spell.execute(character, null);
                return;
            }

            // Atack Spell
            Map<String, Character> characterMap = this.getVisibleCharacterMap(character, null);
            String choice = this.inputService.getChoice(characterMap.keySet());
            Character target = characterMap.get(choice);
            spell.execute(character, target);
        });
    }

    public void useWeapon(Character character) {
        character.getWeaponAction().ifPresent(weapon -> {
            Map<String, Character> characterMap =
                this.getVisibleCharacterMap(character, enemy -> {
                    final int distance = PositionUtils.manhattanDistance(enemy.getPosition(), character.getPosition());
                    return distance <= weapon.getDistance();
                });

            String choice = this.inputService.getChoice(characterMap.keySet());
            Character target = characterMap.get(choice);
            weapon.attack(target);
        });
    }

    private Map<String, Character> getVisibleCharacterMap(Character hero, Predicate<Character> filter) {
        return VisibilityService.getVisible(hero.getPosition(), this.board).stream()
            .map(this.board::getCharacter)
            .filter(Optional::isPresent)
            .map(Optional::get)
            .filter(it -> it != hero)
            .filter(it -> filter == null || filter.test(it))
            .collect(Collectors.toMap(Character::toString, it -> it));
    }
}
