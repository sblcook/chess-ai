import Board.Board;
import Enums.Colors;
import Enums.Result;
import Player.Player;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class GameTest {
    private Board gameBoard;
    private Map<Player, List<Move> > playedMoves;
    private Player turn;
    private Player player1;
    private Player player2;
    private Map<Player, Colors> playerColor;
    private Map<Player, Result> result;

    @Test
    public void boardTest() throws Exception {
        Game newGame = new Game();
        newGame.setGameBoard(gameBoard);
        assertEquals(gameBoard,newGame.getGameBoard());
    }

    @Test
    public void playedMovesTest() throws Exception {
        Game newGame = new Game();
        newGame.setPlayedMoves(playedMoves);
        assertEquals(playedMoves,newGame.getPlayedMoves());
    }

    @Test
    public void turnTest() throws Exception {
        Game newGame = new Game();
        newGame.setTurn(turn);
        assertEquals(turn,newGame.getTurn());
    }

    @Test
    public void player1Test() throws Exception {
        Game newGame = new Game();
        newGame.setPlayer1(player1);
        assertEquals(player1,newGame.getPlayer1());
    }

    @Test
    public void player2Test() throws Exception {
        Game newGame = new Game();
        newGame.setPlayer2(player2);
        assertEquals(player2,newGame.getPlayer2());
    }

    @Test
    public void playerColorTest() throws Exception {
        Game newGame = new Game();
        newGame.setPlayerColor(playerColor);
        assertEquals(playerColor,newGame.getPlayerColor());
    }

    @Test
    public void resultTest() throws Exception {
        Game newGame = new Game();
        newGame.setResult(result);
        assertEquals(result,newGame.getResult());
    }

}
