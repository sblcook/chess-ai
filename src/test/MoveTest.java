import Board.Tile;
import Enums.Colors;
import Pieces.Bishop;
import Pieces.Piece;
import Pieces.Rook;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MoveTest {
    Tile source = new Tile(1,1);
    Tile destination = new Tile(2,2);
    Piece movedPiece = new Rook(Colors.BLACK, source);
    Piece capturedPiece = new Bishop(Colors.WHITE, destination);
    @Test
    public void moveTest() throws Exception {
        Move move = new Move(source,destination,movedPiece,null);
        assertEquals(Colors.BLACK, movedPiece.getPieceColor());
        assertEquals(source, movedPiece.getLocation());
        assertEquals(destination, capturedPiece.getLocation());
    }

    @Test
    public void moveTestCapture() throws Exception {
        //Piece piece = new Bishop(Colors.BLACK, tile);
        Move move = new Move(source,destination,movedPiece,capturedPiece);
        assertEquals(Colors.BLACK, movedPiece.getPieceColor());
        assertEquals(Colors.WHITE, capturedPiece.getPieceColor());
        assertEquals(source, movedPiece.getLocation());
        assertEquals(destination, capturedPiece.getLocation());
    }



}