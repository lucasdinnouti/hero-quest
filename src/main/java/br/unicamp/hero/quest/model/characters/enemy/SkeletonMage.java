package br.unicamp.hero.quest.model.characters.enemy;

import br.unicamp.hero.quest.model.actions.spell.Spell;
import br.unicamp.hero.quest.model.actions.spell.attack.MagicMissile;
import java.util.ArrayList;
import java.util.Optional;

public class SkeletonMage extends Enemy {

    public SkeletonMage(int x, int y, String name) {
        super(3, 2, 8, 2, new ArrayList<>(), x, y, name);
    }

    @Override
    public Optional<Spell> getSpellAction() {
        return Optional.of(new MagicMissile());
    }
}
