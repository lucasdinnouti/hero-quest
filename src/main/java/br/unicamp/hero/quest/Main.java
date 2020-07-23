package br.unicamp.hero.quest;

import br.unicamp.hero.quest.model.board.*;
import br.unicamp.hero.quest.model.characters.hero.*;

public class Main {
    public static void main(String[] args) {
        final Hero hero = new Barbarian(2, 1);

        final Board board = new Board(hero);
        System.out.println(board.toString());
    }
}
