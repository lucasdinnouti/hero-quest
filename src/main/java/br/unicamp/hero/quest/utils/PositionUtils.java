package br.unicamp.hero.quest.utils;

import br.unicamp.hero.quest.constant.Direction;
import br.unicamp.hero.quest.model.Point;
import java.util.List;
import java.util.Random;

public class PositionUtils {

    private static final List<Direction> allDirections = List.of(
        Direction.DOWN, Direction.UP, Direction.LEFT, Direction.RIGHT
    );

    public static Point plusDirection (Point point, Direction direction) {
        return new Point(
            point.getX() + direction.getX(),
            point.getY() + direction.getY()
        );
    }

    public static Direction randomDirection() {
        return allDirections.get(new Random().nextInt(allDirections.size()));
    }
}
