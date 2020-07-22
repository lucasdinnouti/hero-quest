package br.unicamp.hero.quest.model.actions.weapon;

import br.unicamp.hero.quest.model.actions.*;
import br.unicamp.hero.quest.model.characters.Character;

public abstract class Weapon implements Action<Character> {

    private final int attack;
    private final int distance;

    public Weapon(int attack, int distance) {
        this.attack = attack;
        this.distance = distance;
    }

    public int getAttack() {
        return attack;
    }

    public int getDistance() {
        return distance;
    }

    @Override
    public void execute(Character target) {
        this.attack(target);
    }

    public abstract void attack(Character target);
}
