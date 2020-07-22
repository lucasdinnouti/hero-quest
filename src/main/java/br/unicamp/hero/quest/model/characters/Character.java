package br.unicamp.hero.quest.model.characters;

import br.unicamp.hero.quest.model.actions.Action;

public abstract class Character {
    private final int attack;
    private final int defense;
    private final int iq;

    private final int maxHp;
    private int hp;

    public Character(int attack, int defense, int hp, int iq) {
        this.attack = attack;
        this.defense = defense;
        this.maxHp = hp;
        this.hp = hp;
        this.iq = iq;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public int getIq() {
        return iq;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        if (hp > maxHp) {
            this.hp = maxHp;
        } else {
            this.hp = Math.max(hp, 0);
        }
    }

    public abstract Action<?> getRoundAction();
}