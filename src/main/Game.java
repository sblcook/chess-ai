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
