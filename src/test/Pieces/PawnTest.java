package Pieces;

import Board.Tile;
import Enums.Colors;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class PawnTest {
    Tile tile = new Tile(1,1,null);

    @Test
    public void pawnTest() throws Exception {
        Piece piece = new Pawn(Colors.BLACK, tile);
        assertEquals(Colors.BLACK, piece.getPieceColor());
        assertEquals(tile, piece.getLocation());
    }
}