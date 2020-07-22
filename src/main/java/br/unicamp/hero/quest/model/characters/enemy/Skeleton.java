package br.unicamp.hero.quest.model.characters.enemy;

import br.unicamp.hero.quest.model.actions.weapon.Dagger;
import br.unicamp.hero.quest.model.actions.weapon.LongSword;
import br.unicamp.hero.quest.model.actions.weapon.ShortSword;
import br.unicamp.hero.quest.model.characters.hero.Hero;
import java.util.ArrayList;
import java.util.List;

public class Skeleton extends Hero {

    public Skeleton() {
        super(3, 2, 8, 2, new ArrayList<>() {{
            this.add(
                List.of(
                    new LongSword(),
                    new ShortSword(),
                    new Dagger()
                ).get((int)(Math.random() * 3))
            );
        }});
    }
}
