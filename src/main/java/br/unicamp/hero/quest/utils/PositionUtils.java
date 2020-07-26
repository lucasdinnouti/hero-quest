package br.unicamp.hero.quest.utils;

import br.unicamp.hero.quest.constant.*;
import br.unicamp.hero.quest.model.*;

import java.util.*;
import java.util.AbstractMap.SimpleEntry;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PositionUtils {

    public static final List<Direction> allDirections = List.of(
        Direction.DOWN, Direction.UP, Direction.LEFT, Direction.RIGHT
    );

    public static Point plusDirection(Point point, Direction direction) {
        return new Point(
            point.getX() + direction.getX(),
            point.getY() + direction.getY()
        );
    }

    public static Direction randomDirection() {
        return allDirections.get(new Random().nextInt(allDirections.size()));
    }

    public static int manhattanDistance(Point p1, Point p2) {
        return Math.abs(p1.getX() - p2.getX()) + Math.abs(p1.getY() - p2.getY());
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

    public static ArrayList<Point> surroundingsOf(Point point, int radius) {
        ArrayList<Point> result = new ArrayList<>();

        for (int i = -radius; i < radius; i++) {
            for (int j = -radius; j < radius; j++) {
                if (Math.abs(i) + Math.abs(j) < radius) {
                    result.add(new Point(i, j));
                }
            }
        }

        return result;
    }
}
