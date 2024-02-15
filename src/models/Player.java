package models;

import java.util.Scanner;

public class Player {
    private String name;
    private char symbol;
    private PlayerType playerType;

    public Player(String name, char symbol, PlayerType playerType) {
        this.name = name;
        this.symbol = symbol;
        this.playerType = playerType;
    }

    public Move decideMove(Board board) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter row: ");
        int row = sc.nextInt();

        System.out.println("Please enter column: ");
        int column = sc.nextInt();

        return new Move(this, new Cell(row, column));
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

}
