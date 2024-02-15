package strategies.gamewinningstrategy;


import models.Board;
import models.Cell;
import models.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrderOneGameStrategy implements GameWinningStrategy {
    private List<HashMap<Character, Integer>> rowSymbolCounts = new ArrayList<>();
    private List<HashMap<Character, Integer>> colSymbolCounts = new ArrayList<>();
    private HashMap<Character, Integer> topLeftDigSymbolCounts = new HashMap<>();
    private HashMap<Character, Integer> topRightDigSymbolCounts = new HashMap<>();

    public OrderOneGameStrategy(int dimension) {
        for(int i=0; i<dimension; i++) {
            rowSymbolCounts.add(new HashMap<>());
            colSymbolCounts.add(new HashMap<>());
        }
    }

    public boolean isCeilOnTopLeftDig(int row, int col) {
        return row == col;
    }

    public boolean isCeilOnTopRightDig(int row, int col, int dimension) {
        return row + col == dimension - 1;
    }

    @Override
    public boolean checkWinner(Board board, Player lastMovePlayer, Cell moveCell) {
        char symbol = moveCell.getPlayer().getSymbol();
        int row = moveCell.getRow();
        int col = moveCell.getColumn();
        int dimension = board.getBoard().size();

        if (!rowSymbolCounts.get(row).containsKey(symbol)) {
            rowSymbolCounts.get(row).put(symbol, 0);
        }

        rowSymbolCounts.get(row).put(
                symbol,
                rowSymbolCounts.get(row).get(symbol) + 1
        );

        if (!colSymbolCounts.get(col).containsKey(symbol)) {
            colSymbolCounts.get(col).put(symbol, 0);
        }

        colSymbolCounts.get(col).put(
                symbol,
                colSymbolCounts.get(col).get(symbol) + 1
        );

        if (isCeilOnTopLeftDig(row, col)) {
            if (!topLeftDigSymbolCounts.containsKey(symbol)) {
                topLeftDigSymbolCounts.put(symbol, 0);
            }

            topLeftDigSymbolCounts.put(
                    symbol,
                    topLeftDigSymbolCounts.get(symbol) + 1
            );
        }

        if (isCeilOnTopRightDig(row, col, board.getBoard().size())) {
            if (!topRightDigSymbolCounts.containsKey(symbol)) {
                topRightDigSymbolCounts.put(symbol, 0);
            }

            topRightDigSymbolCounts.put(
                    symbol,
                    topRightDigSymbolCounts.get(symbol) + 1
            );
        }

        if (rowSymbolCounts.get(row).get(symbol) == dimension || colSymbolCounts.get(col).get(symbol) == dimension) {
            return true;
        }

        if (isCeilOnTopRightDig(row, col, dimension) && topRightDigSymbolCounts.get(symbol) == dimension) {
            return true;
        }

        if (isCeilOnTopLeftDig(row, col) && topLeftDigSymbolCounts.get(symbol) == dimension) {
            return true;
        }

        return false;

    }
}
