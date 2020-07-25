package br.unicamp.hero.quest.model.actions.spell;

import br.unicamp.hero.quest.model.actions.*;
import br.unicamp.hero.quest.model.characters.Character;
import br.unicamp.hero.quest.service.*;

public abstract class Spell implements Action {
    public void execute(Character source, Character target) {
        if (DiceService.rollDie(6) < source.getIq()) {
            this.cast(source, target);
        }
    }

    protected abstract void cast(Character source, Character target);
}
