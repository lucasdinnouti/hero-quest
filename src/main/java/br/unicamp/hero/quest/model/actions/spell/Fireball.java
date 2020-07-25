package br.unicamp.hero.quest.model.actions.spell;

import br.unicamp.hero.quest.model.*;
import br.unicamp.hero.quest.model.board.*;
import br.unicamp.hero.quest.model.characters.Character;

import java.util.function.*;

public class Fireball extends Spell {
    private final Board board;

    public Fireball(Board board) {
        this.board = board;
    }

    @Override
    protected void cast(Character source, Character target) {
        target.setHp(target.getHp() - 6);

        final Point center = target.getPosition();
        final int x = center.getX();
        final int y = center.getY();

        final Consumer<Character> hitAdjacent = this.hitAdjacent(source);
        board.getCharacter(x - 1, y).ifPresent(hitAdjacent);
        board.getCharacter(x + 1, y).ifPresent(hitAdjacent);
        board.getCharacter(x, y - 1).ifPresent(hitAdjacent);
        board.getCharacter(x, y + 1).ifPresent(hitAdjacent);
    }

    private Consumer<Character> hitAdjacent(Character caster) {
        return (target) -> {
            if (caster == target) {
                target.setHp(target.getHp() - 4);
            }
        };
    }
}
