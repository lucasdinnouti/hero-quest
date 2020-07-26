package br.unicamp.hero.quest.model.characters.hero;

import br.unicamp.hero.quest.utils.*;

import java.util.*;

public class Dwarf extends Hero {
    public Dwarf(int x, int y, String name) {
        super(2, 2, 7, 3, List.of(ActionFactories.getInstance().SHORT_SWORD_FACTORY.get()), x, y, name);
    }
}
