package strategies.botplayingstrategy;

import models.*;

public class RandomBotPlayingStrategy implements BotPlayingStrategy {

    @Override
    public Move decideMove(Player player, Board board) {

        int boardSize = board.getBoard().size();
        for(int i=0; i<boardSize; i++) {
            for(int j=0; j<boardSize; j++) {
                if(board.getBoard().get(i).get(j).getCellState().equals(CellState.EMPTY)) {
                    return new Move(player, new Cell(i, j));
                }
            }
        }

        return null;
    }
}
