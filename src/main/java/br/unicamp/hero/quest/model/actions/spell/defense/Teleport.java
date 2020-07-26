package br.unicamp.hero.quest.model.actions.spell.defense;

import br.unicamp.hero.quest.model.*;
import br.unicamp.hero.quest.model.characters.Character;
import br.unicamp.hero.quest.service.*;

public class Teleport extends DefenseSpell {
    private final MovementService movementService;
    private final Point destination;

    public Teleport(MovementService movementService, Point destination) {
        this.movementService = movementService;
        this.destination = destination;
    }

    @Override
    protected void cast(Character source, Character target) {
        movementService.moveCharacter(target, this.destination);
    }
}
