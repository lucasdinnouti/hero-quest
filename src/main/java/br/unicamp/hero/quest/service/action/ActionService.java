package br.unicamp.hero.quest.service.action;

import br.unicamp.hero.quest.model.actions.spell.defense.*;
import br.unicamp.hero.quest.model.board.*;
import br.unicamp.hero.quest.model.characters.Character;
import br.unicamp.hero.quest.service.*;
import br.unicamp.hero.quest.utils.*;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public abstract class ActionService {
    private final Board board;

    protected ActionService(Board board) {
        this.board = board;
    }

    public void castSpell(Character character) {
        character.getSpellAction().ifPresent(spell -> {
            if (spell instanceof DefenseSpell) {
                spell.execute(character, null);
                return;
            }

            // Attack Spell
            Map<String, Character> characterMap = this.getVisibleCharacterMap(character, null);
            Optional<Character> targetOptional = this.getTarget(characterMap);
            targetOptional.ifPresent(target -> spell.execute(character, target));
        });

        board.removeCorpses();
    }

    public void useWeapon(Character character) {
        character.getWeaponAction().ifPresent(weapon -> {
            Map<String, Character> characterMap =
                this.getVisibleCharacterMap(character, enemy -> {
                    final int distance = PositionUtils.manhattanDistance(enemy.getPosition(), character.getPosition());
                    return distance <= weapon.getDistance();
                });

            Optional<Character> targetOptional = this.getTarget(characterMap);
            targetOptional.ifPresent(target -> weapon.execute(character, target));
        });

        board.removeCorpses();
    }

    private Map<String, Character> getVisibleCharacterMap(Character character, Predicate<Character> filter) {
        return VisibilityService.getVisible(character.getPosition(), this.board).stream()
            .map(this.board::getCharacter)
            .filter(Optional::isPresent)
            .map(Optional::get)
            .filter(it -> it != character)
            .filter(it -> filter == null || filter.test(it))
            .collect(Collectors.toMap(Character::getName, it -> it, (p, q) -> p));
    }

    protected abstract Optional<Character> getTarget(Map<String, Character> characterMap);
}
