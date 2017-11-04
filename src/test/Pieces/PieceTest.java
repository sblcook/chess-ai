package Pieces;

import Board.Tile;
import Enums.Colors;
import Enums.PieceType;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class PieceTest {

    Tile tile = new Tile(2, 4);
    Piece piece = new Piece(Colors.BLACK, null, PieceType.KING); //tile null to be set in getter test

    @Test
    public void getPieceColorTest() throws Exception {
        assertEquals(Colors.BLACK, piece.getPieceColor());
    }

    @Test
    public void getLocationTest() throws Exception {
        piece.setLocation(tile);
        assertEquals(tile, piece.getLocation());
    }

    @Test
    public void getPieceTypeTest() throws Exception {
        assertEquals(PieceType.KING, piece.getPieceType());
    }

    @Test
    public void pieceTest() throws Exception{
        Piece constructorPiece = new Piece(Colors.WHITE, tile, PieceType.QUEEN);
        assertEquals(Colors.WHITE, constructorPiece.getPieceColor());
        assertEquals(tile, constructorPiece.getLocation());
        assertEquals(PieceType.QUEEN, constructorPiece.getPieceType());
    }

}