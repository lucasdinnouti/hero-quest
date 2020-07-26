package br.unicamp.hero.quest.model.characters.enemy;

import br.unicamp.hero.quest.model.actions.weapon.Dagger;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Goblin extends Enemy {
    public Goblin(int x, int y, String name) {
        super(
            1, 1, 5, 1,
            new ArrayList<>(IntStream.range(0, (int) (Math.random() * 10)).mapToObj(it -> new Dagger()).collect(Collectors.toList())),
            x, y, name
        );
    }
}
