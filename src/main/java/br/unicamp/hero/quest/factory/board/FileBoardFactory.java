package br.unicamp.hero.quest.factory.board;

import br.unicamp.hero.quest.model.board.*;
import br.unicamp.hero.quest.model.characters.hero.*;

import java.io.*;
import java.net.*;
import java.nio.file.*;
import java.util.*;
import java.util.regex.*;
import java.util.stream.*;

public class FileBoardFactory implements BoardFactory {
    private final Scanner fileScanner;

    public FileBoardFactory(URL filePath) throws URISyntaxException, FileNotFoundException {
        final File file = Paths.get(filePath.toURI()).toFile();
        this.fileScanner = new Scanner(file);
    }

    @Override
    public Board getBoard() {
        final List<String> lines = this.fileScanner
            .useDelimiter("\n")
            .tokens()
            .collect(Collectors.toList());

        this.fileScanner.close();
        final int sizeY = lines.size();
        final int sizeX = lines.get(0).length() / 2;

        final Pattern matcher = Pattern.compile(".{2}");
        final List<TileType> map = lines.stream()
            .flatMap(line -> matcher.matcher(line).results().map(MatchResult::group))
            .map(this::stringToTileType)
            .collect(Collectors.toList());

        return new Board(sizeX, sizeY, map);
    }

    private TileType stringToTileType(String s) {
        if ("XX".equals(s)) {
            return TileType.WALL;
        }

        return TileType.PATH;
    }
}
