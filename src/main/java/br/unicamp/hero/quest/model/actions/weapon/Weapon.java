package br.unicamp.hero.quest.model.actions.weapon;

import br.unicamp.hero.quest.model.actions.*;
import br.unicamp.hero.quest.model.characters.Character;

public abstract class Weapon implements Action<Character> {

    private final int attack;

    public Weapon(int attack) {
        this.attack = attack;
    }

    public int getAttack() {
        return attack;
    }

    @Override
    public void execute(Character target) {
        this.attack(target);
    }

    public abstract void attack(Character target);
}
