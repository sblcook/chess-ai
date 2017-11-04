package Pieces;

import Board.Tile;
import Enums.Colors;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class BishopTest {

    Tile tile = new Tile(1,1);

    @Test
    public void kingTest() throws Exception {
        Piece piece = new Bishop(Colors.BLACK, tile);
        assertEquals(Colors.BLACK, piece.getPieceColor());
        assertEquals(tile, piece.getLocation());
    }

}