package br.unicamp.hero.quest.model.characters.enemy;

import br.unicamp.hero.quest.model.actions.weapon.*;
import br.unicamp.hero.quest.model.characters.hero.*;
import br.unicamp.hero.quest.utils.*;

import java.util.*;

public class Skeleton extends Enemy {
    public Skeleton(int x, int y, String name) {
        super(3, 2, 8, 2, new ArrayList<>() {{
            this.add(
                List.of(
                    ActionFactories.getInstance().LONG_SWORD_FACTORY.get(),
                    ActionFactories.getInstance().SHORT_SWORD_FACTORY.get(),
                    ActionFactories.getInstance().DAGGER_FACTORY.get()
                ).get((int) (Math.random() * 3))
            );
        }}, x, y, name);
    }
}
