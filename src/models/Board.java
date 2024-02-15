package models;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private List<List<Cell>> board;

    public Board(int dimension) {
        this.board = new ArrayList<>();

        for(int i=0; i<dimension; i++) {
            this.board.add(new ArrayList<>());

            for(int j=0; j<dimension; j++) {
                this.board.get(i).add(new Cell(i, j));
            }
        }
    }

    public void displayBoard() {
        int boardSize = board.size();

        for (List<Cell> cells : board) {
            for (int j = 0; j < boardSize; j++) {
                if (cells.get(j).getCellState().equals(CellState.EMPTY)) {
                    System.out.printf("|   |");
                } else {
                    System.out.printf("| " + cells.get(j).getPlayer().getSymbol() + " |");
                }
            }
            System.out.println("\n");
        }
    }

    public List<List<Cell>> getBoard() {
        return this.board;
    }

    public void setBoard(List<List<Cell>> board) {
        this.board = board;
    }
}
