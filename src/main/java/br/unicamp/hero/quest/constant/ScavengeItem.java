package br.unicamp.hero.quest.constant;

public enum ScavengeItem {
    DAGGER(0.10),
    LONG_SWORD(0.02),
    SHORT_SWORD(0.05),
    MAGIC_MISSILE(0.02),
    FIREBALL(0.02),

    GOBLIN(0.05),
    SKELETON(0.02),
    SKELETON_MAGE(0.01);

    private final double odd;

    ScavengeItem(double walkCommand) {
        this.odd = walkCommand;
    }

    public double getOdd() {
        return odd;
    }
}

