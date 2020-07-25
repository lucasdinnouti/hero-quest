package br.unicamp.hero.quest.model.characters.hero;

import br.unicamp.hero.quest.model.actions.spell.MagicMissile;
import br.unicamp.hero.quest.model.actions.spell.Fireball;
import java.util.List;

public class Mage extends Hero {
    public Mage(int x, int y) {
        super(
            1, 2, 4, 6,
            List.of(
                new MagicMissile(),
                new MagicMissile(),
                new MagicMissile()
                // new Fireball()
            ),
            x, y
        );
    }
}
