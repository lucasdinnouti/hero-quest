package br.unicamp.hero.quest;

import br.unicamp.hero.quest.controller.*;
import br.unicamp.hero.quest.factory.board.*;
import br.unicamp.hero.quest.model.characters.enemy.Enemy;
import br.unicamp.hero.quest.model.characters.enemy.Goblin;
import br.unicamp.hero.quest.model.characters.enemy.Skeleton;
import br.unicamp.hero.quest.model.characters.enemy.SkeletonMage;
import br.unicamp.hero.quest.model.characters.hero.*;
import br.unicamp.hero.quest.service.input.*;
import br.unicamp.hero.quest.service.render.*;

import java.net.*;

public class Main {
    public static void main(String[] args) throws Exception {
        final Hero igor = new Barbarian(15, 4, "Igor");

        final Enemy goblin = new Goblin(2, 10, "Gilberto");
        final Enemy skeleton = new Skeleton(15, 1, "Selton");
        final Enemy skeletonMage = new SkeletonMage(15, 10, "Selton Mario");

        final URL filePath = Main.class.getClassLoader().getResource("map.txt");
        if (filePath == null) {
            throw new Exception("Could not find map file!");
        }

        final BoardFactory boardFactory = new FileBoardFactory(filePath);
        final KeyboardInputService keyboardInputService = new KeyboardInputService(System.in);
        final RenderService renderService = new TerminalRenderService();

        GameController gameController = new GameController(boardFactory, keyboardInputService, renderService);
        gameController.addCharacters(igor, goblin, skeleton, skeletonMage);
        gameController.startGame();

        renderService.displayMessage(" ".repeat(30) + "ROUND OVER");
    }
}
