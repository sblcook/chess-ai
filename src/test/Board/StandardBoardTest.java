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

import static Enums.Colors.WHITE;
import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertArrayEquals;

public class StandardBoardTest {

    private Board board = new StandardBoard();
    private Tile[][] tiles = board.getTiles();
    List<Piece> blackPieceList = board.getBlackPieceList();
    List<Piece> whitePieceList = board.getWhitePieceList();

    @Test
    public void createTiles() throws Exception {
        Tile[][] tileGrid = board.getTiles();
        for (int i = 0; i < 8; i++) {
            Tile[] expected = new Tile[8];
            for (int j = 0; j < 8; j++) {
                expected[j] = new Tile(i + 1, j + 1);
            }
            assertArrayEquals(expected, tileGrid[i]);
        }
    }

    @Test
    public void createBlackPieceList() throws Exception {
        List<Piece> expected = new LinkedList<>();

        expected.add(new Rook(Colors.BLACK, tiles[7][0]));
        expected.add(new Knight(Colors.BLACK, tiles[7][1]));
        expected.add(new Bishop(Colors.BLACK, tiles[7][2]));
        expected.add(new Queen(Colors.BLACK, tiles[7][3]));
        expected.add(new King(Colors.BLACK, tiles[7][4]));
        expected.add(new Bishop(Colors.BLACK, tiles[7][5]));
        expected.add(new Knight(Colors.BLACK, tiles[7][6]));
        expected.add(new Rook(Colors.BLACK, tiles[7][7]));

        for (int i = 0; i < 8; i++) {
            expected.add(new Pawn(Colors.BLACK, tiles[6][i]));
        }

        assertEquals(expected, blackPieceList);
    }

    @Test
    public void createWhitePieceList() throws Exception {
        List<Piece> expected = new LinkedList<>();

        expected.add(new Rook(WHITE, tiles[0][0]));
        expected.add(new Knight(WHITE, tiles[0][1]));
        expected.add(new Bishop(WHITE, tiles[0][2]));
        expected.add(new Queen(WHITE, tiles[0][3]));
        expected.add(new King(WHITE, tiles[0][4]));
        expected.add(new Bishop(WHITE, tiles[0][5]));
        expected.add(new Knight(WHITE, tiles[0][6]));
        expected.add(new Rook(WHITE, tiles[0][7]));

        for (int i = 0; i < 8; i++) {
            expected.add(new Pawn(WHITE, tiles[1][i]));
        }

        assertEquals(expected, whitePieceList);
    }

    @Test
    public void removeWhitePieceTest() {
        boolean flag = false;
        Board board = new StandardBoard();
        Piece removedPiece = whitePieceList.get(0);
        flag = board.removePiece(removedPiece);

        assertEquals(flag, true);
    }

    @Test
    public void removeBlackPieceTest() {
        boolean flag = false;
        Board board = new StandardBoard();
        Piece removedPiece = blackPieceList.get(1);
        flag = board.removePiece(removedPiece);

        assertEquals(flag, true);
    }

    @Test
    public void moveToEmptyTest() {
        boolean flag = false;
        Board board = new StandardBoard();
        whitePieceList = board.getWhitePieceList();
        Piece movedPiece = whitePieceList.get(0);
        flag = board.move(movedPiece, tiles[4][4]);

        assertEquals(flag, true);
    }

    @Test
    public void moveToCaptureTest() {
        boolean flag = false;
        Board board = new StandardBoard();
        blackPieceList = board.getBlackPieceList();
        Piece movedPiece = blackPieceList.get(0);
        flag = board.move(movedPiece, tiles[0][0]);

        assertEquals(flag, true);
    }

}