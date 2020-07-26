package br.unicamp.hero.quest.service.action;

import br.unicamp.hero.quest.model.board.*;
import br.unicamp.hero.quest.model.characters.Character;
import br.unicamp.hero.quest.service.input.*;

import java.util.*;

public class HeroActionService extends ActionService {
    private final InputService inputService;

    public HeroActionService(Board board, InputService inputService) {
        super(board);
        this.inputService = inputService;
    }

    @Override
    protected Optional<Character> getTarget(Map<String, Character> characterMap) {
        final Set<String> characterNames = characterMap.keySet();
        if (characterNames.isEmpty()) {
            return Optional.empty();
        }

        String choice = this.inputService.getChoice(characterNames);
        return Optional.of(characterMap.get(choice));
    }
}
