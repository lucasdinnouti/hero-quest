package br.unicamp.hero.quest.model.characters.enemy;

import br.unicamp.hero.quest.model.actions.*;
import br.unicamp.hero.quest.model.characters.Character;

import java.util.*;

public abstract class Enemy extends Character {

    private final ArrayList<Action<?>> actions;

    public Enemy(int attack, int defense, int hp, int iq, ArrayList<Action<?>> actions, int x, int y) {
        super(attack, defense, hp, iq, x, y);
        this.actions = actions;
    }

    @Override
    public Action<?> getRoundAction() {
        return this.actions.get(0);
    }
}
