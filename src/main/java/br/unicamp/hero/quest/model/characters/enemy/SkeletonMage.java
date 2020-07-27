package br.unicamp.hero.quest.model.characters.enemy;

import br.unicamp.hero.quest.model.actions.Action;
import br.unicamp.hero.quest.model.actions.spell.attack.MagicMissile;

import java.util.ArrayList;
import java.util.List;

public class SkeletonMage extends Enemy {

    public SkeletonMage(int x, int y, String name) {
        super(3, 2, 8, 2, new ArrayList<>(), x, y, name);
    }

    @Override
    public List<Action> getSpellAction() {
        return List.of((Action) new MagicMissile());
    }
}
