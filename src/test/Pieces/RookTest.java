package Pieces;

import Board.Tile;
import Enums.Colors;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class RookTest {
    Tile tile = new Tile(1,1);

    @Test
    public void rookTest() throws Exception {
        Piece piece = new Rook(Colors.BLACK, tile);
        assertEquals(Colors.BLACK, piece.getPieceColor());
        assertEquals(tile, piece.getLocation());
    }

}