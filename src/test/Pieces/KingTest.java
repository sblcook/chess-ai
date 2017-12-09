package Pieces;

import Board.Tile;
import Enums.Colors;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class KingTest {

    Tile tile = new Tile(1,1, null);

    @Test
    public void kingTest() throws Exception {
        Piece piece = new King(Colors.BLACK, tile);
        assertEquals(Colors.BLACK, piece.getPieceColor());
        assertEquals(tile, piece.getLocation());
    }

}