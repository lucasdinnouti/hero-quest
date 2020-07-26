package br.unicamp.hero.quest.model.actions.spell.defense;

import br.unicamp.hero.quest.model.actions.spell.*;
import br.unicamp.hero.quest.model.characters.Character;
import br.unicamp.hero.quest.service.*;

public class SimpleHeal extends DefenseSpell {
    @Override
    public void cast(Character source, Character target) {
        final int healAmount = DiceService.rollDie(6);
        target.setHp(target.getHp() + healAmount);
    }
}
