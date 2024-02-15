package controllers;

import exceptions.InvalidGameConstructionException;
import models.Game;
import models.GameStatus;
import models.Player;

import java.util.List;

public class GameController {

    public void undo(Game game) {
        game.undo();
    }

    public Game createGame(int dimension, List<Player> playersList) {
        try {
            return Game.getBuilder()
                    .setDimension(dimension)
                    .setPlayers(playersList)
                    .build();
        } catch (Exception err) {
           return null;
        }
    }

    public void displayBoard(Game game) {
        game.displayBoard();
    }

    public GameStatus getGameStatue(Game game) {
        return game.getGameStatus();
    }

    public void executeNextMove(Game game) {
        game.makeNextMove();
    }

    public Player getWinner(Game game) {
        return game.getWinner();
    }
}
