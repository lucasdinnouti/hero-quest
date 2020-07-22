package br.unicamp.hero.quest.model.characters.hero;

import br.unicamp.hero.quest.model.actions.spell.MagicMissile;
import br.unicamp.hero.quest.model.actions.spell.fireball.Fireball;
import java.util.ArrayList;

public class Mage extends Hero {

    public Mage() {
        super(1, 2, 4, 6, new ArrayList<>(){{
            this.add(new MagicMissile());
            this.add(new MagicMissile());
            this.add(new MagicMissile());
            this.add(new Fireball());
//            this.add(new TeleportSpell());
        }});
    }
}
