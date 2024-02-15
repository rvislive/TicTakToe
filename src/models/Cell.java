package models;

public class Cell {
    private Player player;
    private int row;
    private int column;
    private CellState cellState;

    public Cell(int row, int column) {
        this.row = row;
        this.column = column;
        this.cellState = CellState.EMPTY;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getRow() {
        return row;
    }

    public void setColumn(int col) {
        this.column = col;
    }

    public int getColumn() {
        return column;
    }

    public void setCellState(CellState cellState) {
        this.cellState = cellState;
    }

    public CellState getCellState() {
        return cellState;
    }
}
