package br.unicamp.hero.quest.model.characters;

import br.unicamp.hero.quest.model.*;
import br.unicamp.hero.quest.model.actions.Action;

public abstract class Character {
    private final String name;

    private final int attack;
    private final int defense;
    private final int iq;

    private final int maxHp;
    private int hp;

    private final Point position;

    public Character(int attack, int defense, int hp, int iq, int x, int y, String name) {
        this.attack = attack;
        this.defense = defense;
        this.maxHp = hp;
        this.hp = hp;
        this.iq = iq;
        this.position = new Point(x, y);
        this.name = name;
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

    public Point getPosition() {
        return this.position;
    }

    public void setPosition(int x, int y) {
        this.position.setX(x);
        this.position.setY(y);
    }

    public void setPosition(Point point) {
        this.position.setX(point.getX());
        this.position.setY(point.getY());
    }

    public abstract Action<?> getRoundAction();

    public String getName() {
        return name;
    }
}