package Board;

import Enums.Colors;
import Pieces.Bishop;
import Pieces.King;
import Pieces.Knight;
import Pieces.Pawn;
import Pieces.Piece;
import Pieces.Queen;
import Pieces.Rook;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertArrayEquals;

public class StandardBoardTest {

    private Board board = new StandardBoard();
    private List<Piece> expected = new LinkedList<>();
    private Tile[][] tiles = board.createTiles();


    @Test
    public void createTiles() throws Exception {
        Tile[][] tileGrid = board.createTiles();
        for(int i = 0; i < 8; i++){
            Tile[] expected = new Tile[8];
            for (int j = 0; j < 8; j++){
                expected[j] = new Tile(i+1, j+1);
            }
            assertArrayEquals(expected, tileGrid[i]);
        }
    }

    @Test
    public void createBlackPieceList() throws Exception {
        expected.add(new Rook(Colors.BLACK, tiles[7][0]));
        expected.add(new Knight(Colors.BLACK, tiles[7][1]));
        expected.add(new Bishop(Colors.BLACK, tiles[7][2]));
        expected.add(new Queen(Colors.BLACK, tiles[7][3]));
        expected.add(new King(Colors.BLACK, tiles[7][4]));
        expected.add(new Bishop(Colors.BLACK, tiles[7][5]));
        expected.add(new Knight(Colors.BLACK, tiles[7][6]));
        expected.add(new Rook(Colors.BLACK, tiles[7][7]));

        for(int i = 0; i < 8; i++){
            expected.add(new Pawn(Colors.BLACK, tiles[6][i]));
        }

        assertEquals(expected, board.createBlackPieceList(tiles));
    }

    @Test
    public void createWhitePieceList() throws Exception {
    }

}