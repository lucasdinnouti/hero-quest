package br.unicamp.hero.quest.factory.board;

import br.unicamp.hero.quest.model.board.*;
import br.unicamp.hero.quest.model.characters.hero.*;

public interface BoardFactory {
    Board getBoard(Hero hero);
}
