package br.unicamp.hero.quest.model.characters.hero;

import br.unicamp.hero.quest.model.actions.spell.defense.SimpleHeal;
import br.unicamp.hero.quest.model.actions.weapon.ShortSword;
import java.util.List;

public class Elf extends Hero {
    public Elf(int x, int y) {
        super(2, 2, 6, 4, List.of(new ShortSword(), new SimpleHeal()), x, y);
    }
}
