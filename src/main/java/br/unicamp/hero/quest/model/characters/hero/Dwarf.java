package br.unicamp.hero.quest.model.characters.hero;

import br.unicamp.hero.quest.model.actions.weapon.ShortSwordWeapon;
import java.util.ArrayList;

public class Dwarf extends Hero {

    public Dwarf() {
        super(2, 2, 7, 3, new ArrayList<>() {{
            this.add(new ShortSwordWeapon());
        }});
    }
}
