package br.unicamp.hero.quest.model.characters.hero;

import br.unicamp.hero.quest.utils.*;

import java.util.*;

public class Elf extends Hero {
    public Elf(int x, int y, String name) {
        super(
            2, 2, 6, 4,
            List.of(
                ActionFactories.getInstance().SHORT_SWORD_FACTORY.get(),
                ActionFactories.getInstance().SIMPLE_HEAL_FACTORY.get()
            ),
            x, y, name
        );
    }
}
