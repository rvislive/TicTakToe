package strategies.gamewinningstrategy;

import models.Board;
import models.Cell;
import models.Player;


public interface GameWinningStrategy {
    default boolean checkWinner(Board board, Player lastMovePlayer, Cell moveCell) {
        return false;
    }
}
