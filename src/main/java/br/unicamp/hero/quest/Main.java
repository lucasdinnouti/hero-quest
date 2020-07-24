package br.unicamp.hero.quest;

import br.unicamp.hero.quest.controller.GameController;
import br.unicamp.hero.quest.model.board.Board;
import br.unicamp.hero.quest.model.characters.hero.Barbarian;
import br.unicamp.hero.quest.model.characters.hero.Hero;
import br.unicamp.hero.quest.service.KeyboardService;
import br.unicamp.hero.quest.service.render.TerminalRenderService;

public class Main {
    public static void main(String[] args) {
        final Hero hero = new Barbarian(2, 1);

        final Board board = new Board(hero);
        GameController gameController = setupGame(board);

        while (true) {
            gameController.manageRound(hero);
        }
    }

    private static GameController setupGame(Board board) {
        return new GameController(board, new KeyboardService(System.in), new TerminalRenderService());
    }
}
