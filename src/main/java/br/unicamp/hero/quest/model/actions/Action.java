package br.unicamp.hero.quest.model.actions;

public interface Action<T> {
    void execute(T target);
}
