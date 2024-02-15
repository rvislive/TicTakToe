package models;

import exceptions.InvalidGameConstructionException;
import strategies.gamewinningstrategy.GameWinningStrategy;
import strategies.gamewinningstrategy.OrderOneGameStrategy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Game {
    private Board board;
    private List<Player> players;
    private List<Move> moves;
    private GameStatus gameStatus;
    private int nextPlayerIndex;
    private Player winner;
    private GameWinningStrategy gameWinningStrategy;

    public Game() {

    }

    public Board getBoard() {
        return board;
    }

    public void displayBoard() {
        this.board.displayBoard();
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> playerList) {
        this.players = playerList;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moveList) {
        this.moves = moveList;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public int getNextPlayerIndex() {
        return nextPlayerIndex;
    }

    public void makeNextMove() {
        Player toMovePlayer = players.get(nextPlayerIndex);

        System.out.println("It is " + players.get(nextPlayerIndex).getName() + "'s turn.");

        Move move = toMovePlayer.decideMove(this.board);

        // validate move
        int row = move.getCell().getRow();
        int col = move.getCell().getColumn();

        System.out.println("Move happened at: " +
                row + ", " + col + ".");

        board.getBoard().get(row).get(col).setCellState(CellState.FILLED);
        board.getBoard().get(row).get(col).setPlayer(players.get(nextPlayerIndex));

        Move finalMove = new Move(
                players.get(nextPlayerIndex),
                board.getBoard().get(row).get(col)
        );

        this.moves.add(finalMove);

        if (gameWinningStrategy.checkWinner(
                board, players.get(nextPlayerIndex), finalMove.getCell()
        )) {
            gameStatus = GameStatus.ENDED;
            winner = players.get(nextPlayerIndex);
        }

        nextPlayerIndex += 1;
        nextPlayerIndex %= players.size();
    }

    public void setNextPlayerIndex(int nextPlayerIndex) {
        this.nextPlayerIndex = nextPlayerIndex;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public GameWinningStrategy getGameWinningStrategy() {
        return gameWinningStrategy;
    }

    public void setGameWinningStrategy(GameWinningStrategy gameWinningStrategy) {
        this.gameWinningStrategy = gameWinningStrategy;
    }

    public void undo() {
        System.out.println("Need to write the code for this.");
    }

    public static Builder getBuilder() {
        return new Builder();
    }

    public static class Builder {
        private int dimension;
        private List<Player> players;


        public Builder setDimension(int dimension) {
            this.dimension = dimension;
            return this;
        }


        public Builder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        private boolean valid() throws InvalidGameConstructionException {
            if (this.dimension < 3) {
                throw new InvalidGameConstructionException("The Dimension of game can't be less then 3");
            }

            if (this.players.size() != this.dimension - 1) {
                throw new InvalidGameConstructionException("Number of Players must be Dimension - 1");
            }

            // Validate no 2 people with same char
            int noOfBot = 0;
            HashSet<Character> symbols = new HashSet<>();
            for(Player player: players) {
                symbols.add(player.getSymbol());
                if(player.getPlayerType() == PlayerType.BOT) {
                    noOfBot++;
                }
            }

            if(symbols.size() != players.size()) {
                throw new InvalidGameConstructionException("No 2 players has same symbol.");
            }

            // Validate all 1 bot
            if(noOfBot > 1) {
                throw new InvalidGameConstructionException("Only 1 bot is allowed to play the game.");
            }

            return true;
        }

        public Game build() throws InvalidGameConstructionException {
            try {
                valid();
            } catch (Exception e) {
                throw new InvalidGameConstructionException(e.getMessage());
            }

            Game game = new Game();
            game.setGameStatus(GameStatus.IN_PROGRESS);
            game.setPlayers(players);
            game.setMoves(new ArrayList<>());
            game.setBoard(new Board(dimension));
            game.setNextPlayerIndex(0);
            game.setGameWinningStrategy(new OrderOneGameStrategy(dimension));

            return game;
        }
    }
}
