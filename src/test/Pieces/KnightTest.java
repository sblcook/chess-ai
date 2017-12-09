package Pieces;

import Board.Tile;
import Enums.Colors;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class KnightTest {
    Tile tile = new Tile(1,1, null);

    @Test
    public void knightTest() throws Exception {
        Piece piece = new Knight(Colors.BLACK, tile);
        assertEquals(Colors.BLACK, piece.getPieceColor());
        assertEquals(tile, piece.getLocation());
    }

}