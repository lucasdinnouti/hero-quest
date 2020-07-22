package br.unicamp.hero.quest.model.characters.hero;

import br.unicamp.hero.quest.model.actions.spell.MagicMissileSpell;
import br.unicamp.hero.quest.model.actions.spell.fireball.FireballSpell;
import java.util.ArrayList;

public class Mage extends Hero {

    public Mage() {
        super(1, 2, 4, 6, new ArrayList<>(){{
            this.add(new MagicMissileSpell());
            this.add(new MagicMissileSpell());
            this.add(new MagicMissileSpell());
            this.add(new FireballSpell());
//            this.add(new TeleportSpell());
        }});
    }
}
