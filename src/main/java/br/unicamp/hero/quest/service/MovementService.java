package br.unicamp.hero.quest.service;

import br.unicamp.hero.quest.constant.Direction;
import br.unicamp.hero.quest.exception.InvalidPositionException;
import br.unicamp.hero.quest.model.Point;
import br.unicamp.hero.quest.model.board.Board;
import br.unicamp.hero.quest.model.characters.Character;
import br.unicamp.hero.quest.utils.PositionUtils;

import static br.unicamp.hero.quest.constant.InterfaceText.INVALID_POSITION_MESSAGE;

public class MovementService {

    Board board;

    public MovementService(Board board) {
        this.board = board;
    }

    public void moveCharacter(Character character, Direction direction) {
        Point newPosition = PositionUtils.plusDirection(character.getPosition(), direction);

        if (board.isWall(newPosition) || !board.isInsideBoard(newPosition)) {
            throw new InvalidPositionException(String.format(INVALID_POSITION_MESSAGE, character.toString(), newPosition.toString()));
        }

        character.setPosition(newPosition);
    }

    public void moveCharacter(Character character, Point position) {
        if (board.isWall(position)) {
            throw new InvalidPositionException(String.format(INVALID_POSITION_MESSAGE, character.toString(), position.toString()));
        }

        character.setPosition(position);
    }
}
