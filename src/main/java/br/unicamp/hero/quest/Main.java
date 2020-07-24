package br.unicamp.hero.quest;

import br.unicamp.hero.quest.model.board.*;
import br.unicamp.hero.quest.model.characters.hero.*;
import br.unicamp.hero.quest.service.render.*;

public class Main {
    public static void main(String[] args) {
        final Hero hero = new Barbarian(2, 1);

        final Board board = new Board(hero);
        final RenderService renderService = new TerminalRenderService();

        renderService.render(board);
    }
}
