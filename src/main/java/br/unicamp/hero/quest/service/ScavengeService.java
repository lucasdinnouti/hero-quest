package br.unicamp.hero.quest.service;

import br.unicamp.hero.quest.constant.ScavengeItem;
import br.unicamp.hero.quest.model.Point;
import br.unicamp.hero.quest.model.actions.spell.attack.Fireball;
import br.unicamp.hero.quest.model.actions.spell.attack.MagicMissile;
import br.unicamp.hero.quest.model.actions.weapon.Dagger;
import br.unicamp.hero.quest.model.actions.weapon.LongSword;
import br.unicamp.hero.quest.model.actions.weapon.ShortSword;
import br.unicamp.hero.quest.model.board.Board;
import br.unicamp.hero.quest.model.characters.Character;
import br.unicamp.hero.quest.model.characters.enemy.Goblin;
import br.unicamp.hero.quest.model.characters.enemy.Skeleton;
import br.unicamp.hero.quest.model.characters.enemy.SkeletonMage;
import br.unicamp.hero.quest.model.characters.hero.Hero;
import br.unicamp.hero.quest.utils.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class ScavengeService {

    private static int SCAVENGE_RADIUS = 2;

    Board board;

    public ScavengeService(Board board) {
        this.board = board;
    }

    public void scavenge(Character character) {
        if (!(character instanceof Hero)) {
            return;
        }

        List<Point> visibleNeighbours = PositionUtils.surroundingsOf(character.getPosition(), SCAVENGE_RADIUS).stream()
            .filter(it -> VisibilityService.isVisible(character.getPosition(), board, it))
            .collect(Collectors.toList());

        for (Point point : visibleNeighbours) {
            Optional<ScavengeItem> something = isThereSomething();

            if (something.isPresent()) {
                ActionFactories factories = ActionFactories.getInstance();
                switch (something.get()) {
                    case DAGGER:
                        ((Hero) character).addAction(factories.DAGGER_FACTORY.get());
                        break;
                    case LONG_SWORD:
                        ((Hero) character).addAction(factories.LONG_SWORD_FACTORY.get());
                        break;
                    case SHORT_SWORD:
                        ((Hero) character).addAction(factories.SHORT_SWORD_FACTORY.get());
                        break;
                    case MAGIC_MISSILE:
                        ((Hero) character).addAction(factories.MAGIC_MISSILE_FACTORY.get());
                        break;
                    case FIREBALL:
                        ((Hero) character).addAction(factories.FIREBALL_FACTORY.get());
                        break;
                    case GOBLIN:
                        board.addCharacter(new Goblin(point.getX(), point.getY(), generateName()));
                        break;
                    case SKELETON:
                        board.addCharacter(new Skeleton(point.getX(), point.getY(), generateName()));
                        break;
                    case SKELETON_MAGE:
                        board.addCharacter(new SkeletonMage(point.getX(), point.getY(), generateName()));
                        break;
                }
            }
        }
    }

    private String generateName() {
        return "João " + UUID.randomUUID().toString().substring(0, 3);
    }

    private Optional<ScavengeItem> isThereSomething() {
        for (ScavengeItem item : ScavengeItem.values()) {
            if (Math.random() < item.getOdd()) {
                return Optional.of(item);
            }
        }

        return Optional.empty();
    }
}
