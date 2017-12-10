import Board.Board;
import Board.StandardBoard;
import Enums.Colors;
import Enums.Result;
import Move.Move;
import Player.Player;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class GameTest {
    private Board gameBoard = new StandardBoard();
    private Map<Player, List<Move> > playedMoves = new HashMap<Player, List<Move>>();
    private Player turn;
    private Player player1;
    private Player player2;
    private Map<Player, Colors> playerColor = new HashMap<Player, Colors>();
    private Map<Player, Result> result = new HashMap<Player, Result>();
    Game newGame = new Game(player1, player2);

    @Test
    public void boardTest() throws Exception {
        newGame.setGameBoard(gameBoard);
        assertEquals(gameBoard,newGame.getGameBoard());
    }

    @Test
    public void playedMovesTest() throws Exception {
        newGame.setPlayedMoves(playedMoves);
        assertEquals(playedMoves,newGame.getPlayedMoves());
    }

    @Test
    public void turnTest() throws Exception {
        newGame.setTurn(turn);
        assertEquals(turn,newGame.getTurn());
    }

    @Test
    public void playerColorTest() throws Exception {
        newGame.setPlayerColor(playerColor);
        assertEquals(playerColor,newGame.getPlayerColor());
    }

    @Test
    public void resultTest() throws Exception {
        newGame.setResult(result);
        assertEquals(result,newGame.getResult());
    }

}
