package br.unicamp.hero.quest.service;

import br.unicamp.hero.quest.constant.Command;
import br.unicamp.hero.quest.model.board.Board;
import br.unicamp.hero.quest.model.characters.Character;

public class ActionService {
    Board board;

    public ActionService(Board board) {
        this.board = board;
    }

    public void doStuff(Character character, Command command) {

    }
}
