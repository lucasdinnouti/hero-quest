package br.unicamp.hero.quest;

import br.unicamp.hero.quest.controller.*;
import br.unicamp.hero.quest.factory.board.*;
import br.unicamp.hero.quest.model.characters.hero.*;
import br.unicamp.hero.quest.service.input.*;
import br.unicamp.hero.quest.service.render.*;

import java.net.*;

public class Main {
    public static void main(String[] args) throws Exception {
        final Hero hero = new Barbarian(2, 1);

        final URL filePath = Main.class.getClassLoader().getResource("map.txt");
        if (filePath == null) {
            throw new Exception("Could not find map file!");
        }

        final BoardFactory boardFactory = new FileBoardFactory(filePath);
        final KeyboardInputService keyboardInputService = new KeyboardInputService(System.in);
        final RenderService renderService = new TerminalRenderService();
        GameController gameController = new GameController(hero, boardFactory, keyboardInputService, renderService);

        while (true) {
            gameController.manageRound(hero);
            renderService.displayMessage(" ".repeat(30) + "ROUND OVER");
        }
    }
}
