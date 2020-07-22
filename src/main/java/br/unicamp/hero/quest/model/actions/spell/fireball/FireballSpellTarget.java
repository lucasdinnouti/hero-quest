package br.unicamp.hero.quest.model.actions.spell.fireball;

import br.unicamp.hero.quest.model.characters.Character;

public class FireballSpellTarget {
    private final Character mainTarget;
    private final Character[] adjacentTargets;

    public FireballSpellTarget(Character mainTarget, Character[] adjacentTargets) {
        this.mainTarget = mainTarget;
        this.adjacentTargets = adjacentTargets;
    }

    public Character getMainTarget() {
        return mainTarget;
    }

    public Character[] getAdjacentTargets() {
        return adjacentTargets;
    }
}
