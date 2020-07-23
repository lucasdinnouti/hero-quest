package br.unicamp.hero.quest.utils;

import br.unicamp.hero.quest.constant.Direction;
import br.unicamp.hero.quest.model.Point;

public class PositionUtils {

    public static Point plusDirection (Point point, Direction direction) {
        return new Point(
            point.getX() + direction.getX(),
            point.getY() + direction.getY()
        );
    }
}
