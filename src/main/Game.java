import Board.Board;
import Board.StandardBoard;
import Enums.Result;
import Player.Player;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import Player.Player;
import Player.HumanPlayer;

public class Game {

    private Board gameBoard;
    private Map<Player, List<Move> >  playedMoves;
    private Player turn;
    private Player player1;
    private Player player2;
    private Map<Player, Color> playerColor;
    private Map<Player, Result> result;

    public Game()
    {
        gameBoard = new StandardBoard();
        playedMoves = new HashMap<Player, List<Move>>();
        playerColor = new HashMap<Player, Color>();
        result = new HashMap<Player, Result>();
    }
    public Board getGameBoard() {
        return gameBoard;
    }

    public Map<Player, List<Move>> getPlayedMoves() {
        return playedMoves;
    }

    public Player getTurn() {
        return turn;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public Map<Player, Color> getPlayerColor() {
        return playerColor;
    }

    public Map<Player, Result> getResult() {
        return result;
    }

    public void setGameBoard(Board gameBoard) {
        this.gameBoard = gameBoard;
    }

    public void setPlayedMoves(Map<Player, List<Move>> playedMoves) {
        this.playedMoves = playedMoves;
    }

    public void setTurn(Player turn) {
        this.turn = turn;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public void setPlayerColor(Map<Player, Color> playerColor) {
        this.playerColor = playerColor;
    }

    public void setResult(Map<Player, Result> result) {
        this.result = result;
    }

    private void startGame()
    {

    }

    private boolean isEnded()
    {
      boolean flag = false;
     // if ( gameBoard.isCheckMated() == true)
      return flag;
    }
}
