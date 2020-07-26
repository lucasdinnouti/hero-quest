package br.unicamp.hero.quest.model.characters.enemy;

import br.unicamp.hero.quest.model.actions.*;
import br.unicamp.hero.quest.model.characters.Character;

import java.util.*;

public abstract class Enemy extends Character {

    private final List<Action> actions;

    public Enemy(int attack, int defense, int hp, int iq, List<Action> actions, int x, int y, String name) {
        super(attack, defense, hp, iq, x, y, name);
        this.actions = actions;
    }

    @Override
    public List<Action> getSpellAction() {
        return this.actions;
    }

    @Override
    public List<Action> getWeaponAction() {
        return this.actions;
    }

    @Override
    public int getDefenseChance() {
        return 1;
    }

    @Override
    public void addAction(Action action) {
        this.actions.add(action);
    }

    @Override
    public void loseAction(Action action) {
        actions.remove(action);
    }
}
