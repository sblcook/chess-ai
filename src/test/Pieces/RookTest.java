package Pieces;

import Board.Tile;
import Enums.Colors;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class RookTest {
    Tile tile = new Tile(1,1);

    @Test
    public void rookTest() throws Exception {
        Piece rook = new Rook(Colors.BLACK, tile);
        tile.setPiece(rook);
        assertEquals(Colors.BLACK, rook.getPieceColor());
        assertEquals(tile, rook.getLocation());
    }

}