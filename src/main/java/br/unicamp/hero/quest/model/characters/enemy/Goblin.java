package br.unicamp.hero.quest.model.characters.enemy;

import br.unicamp.hero.quest.model.actions.weapon.*;
import br.unicamp.hero.quest.utils.*;

import java.util.*;
import java.util.stream.*;

public class Goblin extends Enemy {
    public Goblin(int x, int y, String name) {
        super(
            1, 1, 5, 1,
            new ArrayList<>(
                IntStream.range(0, (int) (Math.random() * 10))
                    .mapToObj(it -> ActionFactories.getInstance().DAGGER_FACTORY.get())
                    .collect(Collectors.toList())
            ),
            x, y, name
        );
    }
}
