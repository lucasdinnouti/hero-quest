package br.unicamp.hero.quest.service;

import java.util.Random;
import java.util.stream.IntStream;

public class DiceService {
    public static int rollDie(int faces) {
        return new Random().nextInt(faces);
    }

    public static int rollDice(int amount, int faces) {
        return IntStream.range(0, amount).map(
            it -> rollDie(faces)
        ).reduce(0, Integer::sum);
    }
}
