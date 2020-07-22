package br.unicamp.hero.quest.model.characters.enemy;

import br.unicamp.hero.quest.model.actions.weapon.Dagger;
import br.unicamp.hero.quest.model.characters.hero.Hero;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Goblin extends Hero {

    public Goblin() {
        super(1, 1, 5, 1,
            IntStream.range(0, (int)(Math.random() * 10)).mapToObj(it -> new Dagger()).collect(Collectors.toList())
        );
    }
}
