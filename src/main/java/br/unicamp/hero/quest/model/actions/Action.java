package br.unicamp.hero.quest.model.actions;

import br.unicamp.hero.quest.model.characters.Character;

public interface Action {
    void execute(Character source, Character target);
    boolean isDisposable();
}
