package br.unicamp.hero.quest.model.characters.enemy;

import br.unicamp.hero.quest.model.actions.weapon.*;
import br.unicamp.hero.quest.model.characters.hero.*;

import java.util.*;

public class Skeleton extends Hero {
    public Skeleton(int x, int y, String name) {
        super(3, 2, 8, 2, new ArrayList<>() {{
            this.add(
                List.of(
                    new LongSword(),
                    new ShortSword(),
                    new Dagger()
                ).get((int) (Math.random() * 3))
            );
        }}, x, y, name);
    }
}
