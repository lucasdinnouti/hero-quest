package br.unicamp.hero.quest.model.characters.hero;

import br.unicamp.hero.quest.model.actions.weapon.LongSword;
import java.util.List;

public class Barbarian extends Hero {

    public Barbarian(int x, int y, String name) {
        super(3, 2, 8, 2, List.of(new LongSword()), x, y, name);
    }
}
