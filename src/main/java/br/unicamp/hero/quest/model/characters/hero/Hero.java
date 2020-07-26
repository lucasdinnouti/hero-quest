package br.unicamp.hero.quest.model.characters.hero;

import br.unicamp.hero.quest.model.actions.Action;
import br.unicamp.hero.quest.model.characters.Character;
import java.util.List;

public abstract class Hero extends Character {

    private final List<Action<?>> actions;

    public Hero(int attack, int defense, int hp, int iq, List<Action<?>> actions, int x, int y, String name) {
        super(attack, defense, hp, iq, x, y, name);
        this.actions = actions;
    }

    @Override
    public Action<?> getRoundAction() {
        return this.actions.get(0);
    }
}
