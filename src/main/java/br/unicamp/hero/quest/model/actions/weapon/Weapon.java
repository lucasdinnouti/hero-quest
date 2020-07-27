package br.unicamp.hero.quest.model.actions.weapon;

import br.unicamp.hero.quest.model.actions.*;
import br.unicamp.hero.quest.model.characters.Character;
import br.unicamp.hero.quest.service.*;

public abstract class Weapon implements Action {

    private final int attack;
    private final int distance;
    private boolean disposable;

    public Weapon(int attack, int distance, boolean disposable) {
        this.attack = attack;
        this.distance = distance;
        this.disposable = disposable;
    }

    public int getAttack() {
        return attack;
    }

    public int getDistance() {
        return distance;
    }

    @Override
    public void execute(Character source, Character target) {
        final int attack = DiceService.rollDice(source.getAttack() + this.getAttack(), 6, 3);
        final int defense = DiceService.rollDice(target.getDefense(), 6, target.getDefenseChance());

        target.setHp(target.getHp() - Math.max(0, attack - defense));
    }

    @Override
    public boolean isDisposable() {
        return disposable;
    }
}
