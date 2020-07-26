package br.unicamp.hero.quest.model.characters.hero;

import br.unicamp.hero.quest.utils.*;

import java.util.*;

public class Barbarian extends Hero {

    public Barbarian(int x, int y, String name) {
        super(3, 2, 8, 2, List.of(ActionFactories.getInstance().LONG_SWORD_FACTORY.get()), x, y, name);
    }
}
