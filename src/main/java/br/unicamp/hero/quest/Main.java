package br.unicamp.hero.quest;

import br.unicamp.hero.quest.factory.board.*;
import br.unicamp.hero.quest.model.board.*;
import br.unicamp.hero.quest.model.characters.hero.*;
import br.unicamp.hero.quest.service.render.*;

import java.net.*;

public class Main {
    public static void main(String[] args) throws Exception {
        final Hero hero = new Barbarian(2, 1);
        final RenderService renderService = new TerminalRenderService();

        final URL filePath = Main.class.getClassLoader().getResource("map.txt");
        if (filePath == null) {
            throw new Exception("Could not find map file!");
        }

        final BoardFactory boardFactory = new StaticBoardFactory(filePath);
        final Board board = boardFactory.getBoard(hero);
        renderService.render(board);
    }
}
