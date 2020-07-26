package br.unicamp.hero.quest.model.characters.enemy;

import br.unicamp.hero.quest.model.actions.*;
import br.unicamp.hero.quest.model.characters.Character;

import java.util.*;

public abstract class Enemy extends Character {

    private final ArrayList<Action> actions;

    public Enemy(int attack, int defense, int hp, int iq, ArrayList<Action> actions, int x, int y, String name) {
        super(attack, defense, hp, iq, x, y, name);
        this.actions = actions;
    }
}
