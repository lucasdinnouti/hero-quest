package br.unicamp.hero.quest.model.characters.hero;

import br.unicamp.hero.quest.model.actions.spell.SimpleHeal;
import br.unicamp.hero.quest.model.actions.weapon.ShortSwordWeapon;
import java.util.ArrayList;

public class Elf extends Hero {

    public Elf() {
        super(2, 2, 6, 4, new ArrayList<>() {{
            this.add(new ShortSwordWeapon());
            this.add(new SimpleHeal());
        }});
    }
}
