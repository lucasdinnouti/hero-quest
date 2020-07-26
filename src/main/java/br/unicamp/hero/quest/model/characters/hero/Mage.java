package br.unicamp.hero.quest.model.characters.hero;

import br.unicamp.hero.quest.utils.*;

import java.util.*;

public class Mage extends Hero {
    public Mage(int x, int y, String name) {
        super(
            1, 2, 4, 6,
            List.of(
                ActionFactories.getInstance().MAGIC_MISSILE_FACTORY.get(),
                ActionFactories.getInstance().MAGIC_MISSILE_FACTORY.get(),
                ActionFactories.getInstance().MAGIC_MISSILE_FACTORY.get(),
                ActionFactories.getInstance().FIREBALL_FACTORY.get()
            ),
            x, y, name);
    }
}
