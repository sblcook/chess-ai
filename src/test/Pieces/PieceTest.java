package Pieces;

import Board.Tile;
import Enums.Colors;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class PieceTest {

    Tile tile = new Tile(2, 4);
    Piece piece = new Piece(Colors.BLACK, tile);

    @Test
    public void getPieceColorTest() throws Exception {
        assertEquals(Colors.BLACK, piece.getPieceColor());
    }

    @Test
    public void getLocationTest() throws Exception {
        assertEquals(tile, piece.getLocation());
    }

}