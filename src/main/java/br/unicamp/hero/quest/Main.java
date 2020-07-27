package br.unicamp.hero.quest;

import br.unicamp.hero.quest.controller.*;
import br.unicamp.hero.quest.factory.board.*;
import br.unicamp.hero.quest.model.characters.enemy.*;
import br.unicamp.hero.quest.model.characters.hero.*;
import br.unicamp.hero.quest.service.input.*;
import br.unicamp.hero.quest.service.render.*;

import br.unicamp.hero.quest.utils.MessageUtils;
import java.net.*;

public class Main {
    public static void main(String[] args) throws Exception {
        final URL filePath = Main.class.getClassLoader().getResource("map.txt");
        if (filePath == null) {
            throw new Exception("Could not find map file!");
        }

        final BoardFactory boardFactory = new FileBoardFactory(filePath);
        final KeyboardInputService keyboardInputService = new KeyboardInputService(System.in);
        final RenderService renderService = new TerminalRenderService();

        GameController gameController = new GameController(boardFactory, keyboardInputService, renderService);
        addCharacters(gameController);

        try {
            gameController.startGame();
        } catch (Exception e) {
            MessageUtils.displayMessage(String.format("Something wrong happened: %s", e.getMessage()));
        }

        MessageUtils.displayMessage(" ".repeat(30) + "GAME OVER");
    }

    private static void addCharacters(GameController gameController) {
        final Hero hero = new Mage(15, 4, "Hero");
        final Enemy goblin = new Goblin(2, 10, "Gilberto");
        final Enemy skeleton = new Skeleton(15, 1, "Selton");
        final Enemy skeletonMage = new SkeletonMage(15, 10, "Selton Mario");
        gameController.addCharacters(hero, goblin, skeleton, skeletonMage);
    }
}