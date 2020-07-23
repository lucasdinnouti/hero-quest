package br.unicamp.hero.quest.service;

import br.unicamp.hero.quest.constant.Direction;
import br.unicamp.hero.quest.exception.OccupiedPositionException;
import br.unicamp.hero.quest.model.Point;
import br.unicamp.hero.quest.model.board.Board;
import br.unicamp.hero.quest.model.characters.Character;
import br.unicamp.hero.quest.utils.PositionUtils;

import static br.unicamp.hero.quest.constant.InterfaceText.OCCUPIED_POSITION_MESSAGE;

public class MovementService {

    Board board;

    public MovementService(Board board) {
        this.board = board;
    }

    public void moveCharacter(Character character, Direction direction) {
        Point newPosition = PositionUtils.plusDirection(character.getPosition(), direction);

//        if (!board.canMove(newPosition)) {
//            throw new OccupiedPositionException(String.format(OCCUPIED_POSITION_MESSAGE, character.toString(), newPosition.toString()));
//        }

        character.setPosition(newPosition);
    }

    public void moveCharacter(Character character, Point position) {

//        if (!board.canMove(position)) {
//            throw new OccupiedPositionException(String.format(OCCUPIED_POSITION_MESSAGE, character.toString(), position.toString()));
//        }

        character.setPosition(position);
    }
}
