package br.unicamp.hero.quest.model.characters.enemy;

import br.unicamp.hero.quest.model.actions.Action;
import br.unicamp.hero.quest.model.characters.Character;
import java.util.ArrayList;

public abstract class Enemy extends Character {

    private final ArrayList<Action<?>> actions;

    public Enemy(int attack, int defense, int hp, int iq, ArrayList<Action<?>> actions) {
        super(attack, defense, hp, iq);
        this.actions = actions;
    }

    @Override
    public Action<?> getRoundAction() {
        return this.actions.get(0);
    }
}
