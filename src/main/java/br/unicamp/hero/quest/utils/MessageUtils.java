package br.unicamp.hero.quest.utils;

import br.unicamp.hero.quest.constant.Direction;
import br.unicamp.hero.quest.model.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MessageUtils {

    public static final int CONTENT_WIDTH = 80;

    public static void displayMessage(String message) {
        StringBuilder sb = new StringBuilder();

        sb.append("#".repeat(CONTENT_WIDTH));
        sb.append("\n");

        String[] lines = message.split(String.format("(?<=\\G.{%d})", CONTENT_WIDTH - 4));
        for (String line : lines) {
            sb.append("# ");
            sb.append(line);
            sb.append(" ".repeat(CONTENT_WIDTH - 4 - line.length()));
            sb.append(" #");
            sb.append("\n");
        }

        sb.append("#".repeat(CONTENT_WIDTH));
        sb.append("\n");

        System.out.println(sb.toString());
    }
}
