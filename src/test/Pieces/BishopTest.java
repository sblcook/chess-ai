package Pieces;

import Board.Board;
import Board.Tile;
import Enums.Colors;
import org.junit.Test;

import static Enums.PieceType.BISHOP;
import static junit.framework.TestCase.assertEquals;

public class BishopTest {

    Tile tile = new Tile(1,1);

    @Test
    public void bishopTest() throws Exception {
        Piece bishop = new Bishop(Colors.BLACK, tile);
        tile.setPiece(bishop);
        assertEquals(Colors.BLACK, bishop.getPieceColor());
        assertEquals(tile, bishop.getLocation());
    }

}