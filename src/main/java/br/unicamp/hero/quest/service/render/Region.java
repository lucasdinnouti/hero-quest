package br.unicamp.hero.quest.service.render;

import br.unicamp.hero.quest.model.*;

enum Region {
    NW(1, 1),
    NE(-1, 1),
    SW(1, -1),
    SE(-1, -1);

    final int xDiff, yDiff;

    Region(int xDiff, int yDiff) {
        this.xDiff = xDiff;
        this.yDiff = yDiff;
    }

    public static Region fromCenter(int x, int y, Point center) {
        if (y <= center.getY()) {
            return x <= center.getX() ? NW : NE;
        } else {
            return x <= center.getX() ? SW : SE;
        }
    }
}
