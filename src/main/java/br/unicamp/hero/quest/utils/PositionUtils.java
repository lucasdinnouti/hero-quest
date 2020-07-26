package br.unicamp.hero.quest.utils;

import br.unicamp.hero.quest.constant.Direction;
import br.unicamp.hero.quest.model.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class PositionUtils {

    public static final List<Direction> allDirections = List.of(
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

    public static ArrayList<Direction> directionsToPoint(Point source, Point target) {
        int xDelta = target.getX() - source.getX();
        int yDelta = target.getY() - source.getY();

        return vectorComponents(new Point(xDelta, yDelta));
    }


    public static ArrayList<Direction> vectorComponents(Point vector) {
        ArrayList<Direction> directions = new ArrayList<>();

        if (vector.getX() > 0) {
            directions.add(Direction.RIGHT);
        } else {
            directions.add(Direction.LEFT);
        }

        if (vector.getY() > 0) {
            directions.add(Direction.DOWN);
        } else {
            directions.add(Direction.UP);
        }

        if (Math.abs(vector.getX()) < Math.abs(vector.getY())) {
            Collections.reverse(directions);
        }

        return directions;
    }
}
