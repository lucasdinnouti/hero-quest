package br.unicamp.hero.quest.service.action;

import br.unicamp.hero.quest.model.actions.Action;
import br.unicamp.hero.quest.model.board.*;
import br.unicamp.hero.quest.model.characters.Character;
import br.unicamp.hero.quest.model.characters.hero.*;
import br.unicamp.hero.quest.service.input.*;

import java.util.*;

public class EnemyActionService extends ActionService {

    public EnemyActionService(Board board) {
        super(board);
    }

    @Override
    protected Optional<Character> getTarget(Map<String, Character> characterMap) {
        return characterMap.entrySet().stream()
            .filter(it -> it.getValue() instanceof Hero)
            .findFirst()
            .map(Map.Entry::getValue);
    }

    @Override
    protected Optional<Action> chooseAction(Map<String, Action> actionMap) {
        return actionMap.entrySet().stream()
                .findFirst()
                .map(Map.Entry::getValue);
    }
}
