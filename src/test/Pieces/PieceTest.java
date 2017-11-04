package Pieces;

import Enums.Colors;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class PieceTest {

    @Test
    public void getPieceColor() throws Exception {
        Piece piece = new Piece(Colors.BLACK);
        assertEquals(Colors.BLACK, piece.getPieceColor());
    }

}