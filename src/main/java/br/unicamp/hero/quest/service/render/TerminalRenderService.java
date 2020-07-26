package br.unicamp.hero.quest.service.render;

import br.unicamp.hero.quest.model.*;
import br.unicamp.hero.quest.model.board.*;
import br.unicamp.hero.quest.model.characters.Character;
import br.unicamp.hero.quest.service.*;

import java.util.*;
import java.util.stream.*;

public class TerminalRenderService implements RenderService {

    public static final int CONTENT_WIDTH = 80;

    @Override
    public void render(Board board) {
        final Point heroPosition = board.getHero().getPosition();
        final List<Point> visible = VisibilityService.getVisible(heroPosition, board);

        final String map = IntStream.range(0, board.getSizeY()).mapToObj(y ->
            IntStream.range(0, board.getSizeX()).mapToObj(x -> {
                Optional<Character> character = board.getCharacter(x, y);
                if (visible.stream().anyMatch(point -> point.getY() == y && point.getX() == x)) {
                    return character.isPresent() ? "HR" : "  ";
                }

                final TileType tile = board.getTile(x, y);
                if (tile == TileType.PATH) {
                    return "::";
                }

                final Region region = Region.fromCenter(x, y, heroPosition);
                final boolean isVisible = visible.stream()
                    .anyMatch(point ->
                        point.equals(x, y + region.yDiff)
                            || point.equals(x + region.xDiff, y)
                            || point.equals(x + region.xDiff, y + region.yDiff)
                    );

                if (isVisible) {
                    return "XX";
                }

                return "::";
            }).collect(Collectors.joining())
        ).collect(Collectors.joining("\n"));

        System.out.println(map);
    }

    @Override
    public void displayMessage(String message) {
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
