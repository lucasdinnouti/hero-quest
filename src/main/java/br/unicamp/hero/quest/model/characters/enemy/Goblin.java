package br.unicamp.hero.quest.model.characters.enemy;

import br.unicamp.hero.quest.model.actions.weapon.*;
import br.unicamp.hero.quest.model.characters.hero.*;

import java.util.stream.*;

public class Goblin extends Hero {
    public Goblin(int x, int y) {
        super(
            1, 1, 5, 1,
            IntStream.range(0, (int) (Math.random() * 10)).mapToObj(it -> new Dagger()).collect(Collectors.toList()),
            x, y
        );
    }
}
