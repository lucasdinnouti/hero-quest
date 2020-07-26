package br.unicamp.hero.quest.model.characters.hero;

import br.unicamp.hero.quest.model.actions.Action;
import br.unicamp.hero.quest.model.actions.spell.*;
import br.unicamp.hero.quest.model.actions.weapon.*;
import br.unicamp.hero.quest.model.characters.Character;

import java.util.*;

public abstract class Hero extends Character {
    private final List<Action> actions;

    public Hero(int attack, int defense, int hp, int iq, List<Action> actions, int x, int y, String name) {
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
            .filter(it -> it instanceof Weapon)
            .findFirst()
            .map(it -> (Weapon) it);
    }

    @Override
    public int getDefenseChance() {
        return 2;
    }
}
