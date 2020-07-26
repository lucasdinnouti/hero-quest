package br.unicamp.hero.quest.service;

import br.unicamp.hero.quest.model.*;
import br.unicamp.hero.quest.model.board.*;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class VisibilityService {
    public static boolean isVisible(Point center, Board board, Point target) {
        return getVisible(center, board).contains(target);
    }

    public static List<Point> getVisible(Point center, Board board) {
        final Function<Point, List<Point>> bresenhanCurry = bresenhamLine(center);
        return board.getEdges().stream()
            .map(bresenhanCurry)
            .flatMap(ray -> ray.stream().takeWhile(point -> canRayContinue(board, center, point)))
            .collect(Collectors.toList());
    }

    private static Function<Point, List<Point>> bresenhamLine(Point start) {
        return (end) -> {
            int x1 = start.getX();
            int y1 = start.getY();
            int x2 = end.getX();
            int y2 = end.getY();
            final boolean isSteep = Math.abs(y2 - y1) > Math.abs(x2 - x1);

            if (isSteep) {
                x1 = start.getY();
                y1 = start.getX();
                x2 = end.getY();
                y2 = end.getX();
            }

            final boolean isReversed = x1 > x2;
            if (isReversed) {
                int oldX1 = x1;
                x1 = x2;
                x2 = oldX1;

                int oldY1 = y1;
                y1 = y2;
                y2 = oldY1;
            }

            int dx = x2 - x1;
            int dy = Math.abs(y2 - y1);

            int error = (int) (dx / 2.0);
            final int yStep = y1 < y2 ? 1 : -1;

            final List<Point> points = new ArrayList<>();
            for (int x = x1, y = y1; x < x2 + 1; x++) {
                final Point coord = isSteep ? new Point(y, x) : new Point(x, y);
                points.add(coord);

                error -= dy;
                if (error < 0) {
                    y += yStep;
                    error += dx;
                }
            }

            if (isReversed) {
                Collections.reverse(points);
            }

            return points;
        };
    }

    private static boolean canRayContinue(Board board, Point center, Point current) {
        return (!board.isWall(current) && !board.isDoor(current)) || current.equals(center);
    }
}
