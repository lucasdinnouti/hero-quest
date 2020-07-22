package br.unicamp.hero.quest.model.characters.enemy;

import br.unicamp.hero.quest.model.actions.Action;
import br.unicamp.hero.quest.model.actions.spell.MagicMissile;
import br.unicamp.hero.quest.model.characters.hero.Hero;
import java.util.ArrayList;

public class SkeletonMage extends Hero {

    public SkeletonMage() {
        super(3, 2, 8, 2, new ArrayList<>());
    }

    @Override
    public Action<?> getRoundAction() {
        return new MagicMissile();
    }
}
