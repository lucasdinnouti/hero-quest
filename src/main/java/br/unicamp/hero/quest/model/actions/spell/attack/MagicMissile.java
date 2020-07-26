package br.unicamp.hero.quest.model.actions.spell.attack;

import br.unicamp.hero.quest.model.characters.Character;
import br.unicamp.hero.quest.service.*;

public class MagicMissile extends AttackSpell {
    @Override
    public void execute(Character source, Character target) {
        for (int i = 0; i < 3; i++) {
            if (DiceService.rollDie(6) < source.getIq()) {
                this.cast(source, target);
            }
        }
    }

    @Override
    protected void cast(Character source, Character target) {
        target.setHp(target.getHp() - 2);
    }
}
