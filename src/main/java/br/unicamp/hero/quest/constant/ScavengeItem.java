package br.unicamp.hero.quest.constant;

public enum ScavengeItem {
    DAGGER(0.30),
    LONG_SWORD(0.10),
    SHORT_SWORD(0.10),
    MAGIC_MISSILE(0.05),
    FIREBALL(0.05),

    GOBLIN(0.10),
    SKELETON(0.10),
    SKELETON_MAGE(0.02);

    private final double odd;

    ScavengeItem(double walkCommand) {
        this.odd = walkCommand;
    }

    public double getOdd() {
        return odd;
    }
}

