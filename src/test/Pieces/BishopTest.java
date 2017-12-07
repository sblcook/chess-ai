package Pieces;

import Board.Board;
import Board.Tile;
import Enums.Colors;
import org.junit.Test;

import static Enums.PieceType.BISHOP;
import static junit.framework.TestCase.assertEquals;

public class BishopTest {

    Tile tile = new Tile(1,1,null);

    @Test
    public void bishopTest() throws Exception {
        Piece piece = new Bishop(Colors.BLACK, tile);
        assertEquals(Colors.BLACK, piece.getPieceColor());
        assertEquals(tile, piece.getLocation());
        assertEquals(null, piece.getPieceType());
    }

}