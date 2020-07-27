package br.unicamp.hero.quest.service.render;

import br.unicamp.hero.quest.model.*;
import br.unicamp.hero.quest.model.board.*;
import br.unicamp.hero.quest.model.characters.Character;
import br.unicamp.hero.quest.model.characters.enemy.*;
import br.unicamp.hero.quest.service.*;

import java.util.*;
import java.util.stream.*;

public class TerminalRenderService implements RenderService {

    private static final Map<Class<?>, String> displayByClass;
    private static final Map<TileType, String> displayByTileType;

    static {
         displayByClass = Map.of(
             SkeletonMage.class, "⠖⠿",
             Skeleton.class, "⠱⠎",
             Goblin.class, "⠳⠞"
         );

        displayByTileType = Map.of(
            TileType.DOOR, "  ",
            TileType.WALL, "XX",
            TileType.PATH, "  "
        );
    }

    @Override
    public void render(Board board) {
        final Point heroPosition = board.getHero().getPosition();
        final List<Point> visible = VisibilityService.getVisible(heroPosition, board);

        final String map = IntStream.range(0, board.getSizeY()).mapToObj(y ->
            IntStream.range(0, board.getSizeX()).mapToObj(x -> {
                final TileType tile = board.getTile(x, y);
                Optional<Character> character = board.getCharacter(x, y);
                if (visible.stream().anyMatch(point -> point.getY() == y && point.getX() == x)) {
                    return character.isPresent()
                        ? displayByClass.getOrDefault(character.get().getClass(), "HR")
                        : displayByTileType.get(tile);
                }

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
                    return displayByTileType.get(tile);
                }

                return "::";
            }).collect(Collectors.joining())
        ).collect(Collectors.joining("\n"));

        System.out.println(map);
    }
}
