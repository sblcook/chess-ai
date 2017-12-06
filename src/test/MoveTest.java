import Board.Tile;
import Enums.Colors;
import Pieces.Bishop;
import Pieces.Piece;
import Pieces.Rook;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;


public class MoveTest {
    private Tile source = new Tile(1,1);
    private Tile destination = new Tile(2,2);
    private Piece movedPiece = new Rook(Colors.BLACK, source);
    private Piece capturedPiece = new Bishop(Colors.WHITE, destination);

    @Test
    public void moveTest() throws Exception {
        Move move = new Move(source,destination,movedPiece,null);
        Tile moveSource = move.getSource();
        Tile moveDestination = move.getDestination();
        Piece movedMovedPiece = move.getMovedPiece();
        assertEquals(movedMovedPiece,move.getMovedPiece());
        assertEquals(source, moveSource);
        assertEquals(destination, moveDestination);
    }
    @Test
    public void moveTestCapture() throws Exception {
        //Piece piece = new Bishop(Colors.BLACK, tile);
        Move move = new Move(source,destination,movedPiece,capturedPiece);
        Tile moveSource = move.getSource();
        Tile moveDestination = move.getDestination();
        Piece moveMovedPiece = move.getMovedPiece();
        Piece moveCapturedPiece = move.getCapturedPiece();
        assertEquals(source, moveSource);
        assertEquals(destination, moveDestination);
    }



}