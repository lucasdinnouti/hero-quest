package br.unicamp.hero.quest.utils;

import br.unicamp.hero.quest.model.actions.spell.attack.*;
import br.unicamp.hero.quest.model.actions.spell.defense.*;
import br.unicamp.hero.quest.model.actions.weapon.*;
import br.unicamp.hero.quest.model.board.*;

import java.util.function.*;

public class ActionFactories {
    public final Supplier<Fireball> FIREBALL_FACTORY;
    public final Supplier<MagicMissile> MAGIC_MISSILE_FACTORY = MagicMissile::new;
    public final Supplier<SimpleHeal> SIMPLE_HEAL_FACTORY = SimpleHeal::new;

    public final Supplier<LongSword> LONG_SWORD_FACTORY = LongSword::new;
    public final Supplier<ShortSword> SHORT_SWORD_FACTORY = ShortSword::new;
    public final Supplier<Dagger> DAGGER_FACTORY = Dagger::new;

    private static ActionFactories instance;

    private ActionFactories(Board board) {
        FIREBALL_FACTORY = () -> new Fireball(board);
    }

    public static void createInstance(Board board) {
        if (ActionFactories.instance == null) {
            ActionFactories.instance = new ActionFactories(board);
        }
    }

    public static ActionFactories getInstance() {
        return ActionFactories.instance;
    }
}
