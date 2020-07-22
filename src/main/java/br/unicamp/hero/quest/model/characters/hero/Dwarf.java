package br.unicamp.hero.quest.model.characters.hero;

import br.unicamp.hero.quest.model.actions.weapon.ShortSword;
import java.util.List;

public class Dwarf extends Hero {

    public Dwarf() {
        super(2, 2, 7, 3, List.of(new ShortSword()));
    }
}
