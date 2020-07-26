package br.unicamp.hero.quest.model.characters.enemy;

import br.unicamp.hero.quest.model.actions.*;
import br.unicamp.hero.quest.model.actions.spell.*;
import br.unicamp.hero.quest.model.actions.weapon.*;
import br.unicamp.hero.quest.model.characters.Character;

import java.util.*;

public abstract class Enemy extends Character {

    private final ArrayList<Action> actions;

    public Enemy(int attack, int defense, int hp, int iq, ArrayList<Action> actions, int x, int y, String name) {
        super(attack, defense, hp, iq, x, y, name);
        this.actions = actions;
    }

    @Override
    public Optional<Spell> getSpellAction() {
        return this.actions.stream()
            .filter(it -> it instanceof Spell)
            .findFirst()
            .map(it -> (Spell) it);
    }

    @Override
    public Optional<Weapon> getWeaponAction() {
        return this.actions.stream()
            .filter(it -> it instanceof Spell)
            .findFirst()
            .map(it -> (Weapon) it);
    }
}
