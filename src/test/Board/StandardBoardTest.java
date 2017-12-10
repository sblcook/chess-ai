package Board;

import Enums.Colors;
import Pieces.Bishop;
import Pieces.King;
import Pieces.Knight;
import Pieces.Pawn;
import Pieces.Piece;
import Pieces.Queen;
import Pieces.Rook;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static Enums.Colors.BLACK;
import static Enums.Colors.WHITE;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;

public class StandardBoardTest {

    private StandardBoard board = new StandardBoard();
    private Tile[][] tiles = board.getTiles();
    List<Piece> blackPieceList = board.getBlackPieceList();
    List<Piece> whitePieceList = board.getWhitePieceList();
    Tile tile = new Tile(4, 4);

    @Test
    public void ValidQueenMove() throws Exception {
        Piece queen = new Queen(BLACK, tile);
        assertFalse(board.isValidMove(queen, tile));
        assertFalse(board.isValidMove(queen, new Tile(0, 5)));
        assertTrue(board.isValidMove(queen, new Tile(2, 2)));
    }

    @Test
    public void testQueenAttack() throws Exception{
        Piece queen = new Queen(BLACK, tile);
        Tile dest = new Tile(1, 4);
        Piece deadPawn = new Pawn(WHITE, dest);
        dest.setPiece(deadPawn);
        assertTrue(board.isValidMove(queen, dest));
        Piece deadPawnSameColor = new Pawn(BLACK, dest);
        dest.setPiece(deadPawnSameColor);
        assertFalse(board.isValidMove(queen, dest));
    }

    @Test
    public void ValidKingMove() throws Exception {
        Piece king = new King(BLACK, tile);
        assertFalse(board.isValidMove(king, tile));
        assertFalse(board.isValidMove(king, new Tile(0,0)));
        assertTrue(board.isValidMove(king, new Tile(3, 3)));
    }

    @Test
    public void testKingAttack() throws Exception{
        Piece king = new King(BLACK, tile);
        Tile dest = new Tile(5, 4);
        Piece deadPawn = new Pawn(WHITE, dest);
        dest.setPiece(deadPawn);
        assertTrue(board.isValidMove(king, dest));
        Piece deadPawnSameColor = new Pawn(BLACK, dest);
        dest.setPiece(deadPawnSameColor);
        assertFalse(board.isValidMove(king, dest));
    }

    @Test
    public void isValidKnightMove() throws Exception {
        Piece knight = new Knight(BLACK, tile);
        assertFalse(board.isValidMove(knight, tile));
        assertFalse(board.isValidMove(knight, new Tile(4, 5)));
        assertTrue(board.isValidMove(knight, new Tile(2, 5)));
    }

    @Test
    public void testKnightAttack() throws Exception{
        Piece knight = new Knight(BLACK, tile);
        Tile dest = new Tile(6, 3);
        Piece deadPawn = new Pawn(WHITE, dest);
        dest.setPiece(deadPawn);
        assertTrue(board.isValidMove(knight, dest));
        Piece deadPawnSameColor = new Pawn(BLACK, dest);
        dest.setPiece(deadPawnSameColor);
        assertFalse(board.isValidMove(knight, dest));
    }

    @Test
    public void isValidMoveBishop() throws Exception {
        Piece bishop = new Bishop(BLACK, tile);
        assertFalse(board.isValidMove(bishop, tile));
        assertFalse(board.isValidMove(bishop, new Tile(4, 5)));
        assertTrue(board.isValidMove(bishop, new Tile(5, 5)));
    }

    @Test
    public void testBishopAttack() throws Exception {
        Piece bishop = new Bishop(BLACK, tile);
        Tile dest = new Tile(3, 3);
        Piece deadPawn = new Pawn(WHITE, dest);
        dest.setPiece(deadPawn);
        assertTrue(board.isValidMove(bishop, dest));
        Piece deadPawnSameColor = new Pawn(BLACK, dest);
        dest.setPiece(deadPawnSameColor);
        assertFalse(board.isValidMove(bishop, dest));
    }

    @Test
    public void isValidMovePawn() throws Exception {
        Piece pawn = new Pawn(BLACK, tile);
        assertFalse(board.isValidMove(pawn, tile));
        assertTrue(board.isValidMove(pawn, new Tile(3,4)));
    }

    @Test
    public void testPawnAttack() throws Exception {
        Piece pawn = new Pawn(BLACK, tile);
        Tile dest = new Tile(3, 3);
        Piece deadPawn = new Pawn(WHITE, dest);
        dest.setPiece(deadPawn);
        assertTrue(board.isValidMove(pawn, dest));
    }

    @Test
    public void isValidMoveRook() throws Exception {
        Piece rook = new Rook(BLACK, tile);
        assertFalse(board.isValidMove(rook, tile));
        assertTrue(board.isValidMove(rook, new Tile(4, 5)));
    }

    @Test
    public void testRookAttack() throws Exception {
        Piece rook = new Rook(BLACK, tile);
        Tile dest = new Tile(4, 7);
        Piece deadPawn = new Pawn(WHITE, dest);
        dest.setPiece(deadPawn);
        assertTrue(board.isValidMove(rook, dest));
        Piece deadPawnSameColor = new Pawn(BLACK, dest);
        dest.setPiece(deadPawnSameColor);
        assertFalse(board.isValidMove(rook, dest));
    }

    @Test
    public void createTiles() throws Exception {
        Tile[][] tileGrid = board.createTiles();
        for (int i = 0; i < 8; i++) {
            Tile[] expected = new Tile[8];
            for (int j = 0; j < 8; j++){
                expected[j] = new Tile(i+1, j+1);
                if((j+i)%2 == 0){
                    expected[j].setColor(BLACK);
                }else{
                    expected[j].setColor(WHITE);
                }
            }
            assertArrayEquals(expected, tileGrid[i]);
        }
    }

    @Test
    public void createBlackPieceList() throws Exception {
        List<Piece> expected = new LinkedList<>();

        expected.add(new Rook(BLACK, tiles[7][0]));
        expected.add(new Knight(BLACK, tiles[7][1]));
        expected.add(new Bishop(BLACK, tiles[7][2]));
        expected.add(new Queen(BLACK, tiles[7][3]));
        expected.add(new King(BLACK, tiles[7][4]));
        expected.add(new Bishop(BLACK, tiles[7][5]));
        expected.add(new Knight(BLACK, tiles[7][6]));
        expected.add(new Rook(BLACK, tiles[7][7]));

        for (int i = 0; i < 8; i++) {
            expected.add(new Pawn(BLACK, tiles[6][i]));
        }

        assertEquals(expected, blackPieceList);
    }

    @Test
    public void createWhitePieceList() throws Exception {
        List<Piece> expected = new LinkedList<>();

        expected.add(new Rook(WHITE, tiles[0][0]));
        expected.add(new Knight(WHITE, tiles[0][1]));
        expected.add(new Bishop(WHITE, tiles[0][2]));
        expected.add(new Queen(WHITE, tiles[0][3]));
        expected.add(new King(WHITE, tiles[0][4]));
        expected.add(new Bishop(WHITE, tiles[0][5]));
        expected.add(new Knight(WHITE, tiles[0][6]));
        expected.add(new Rook(WHITE, tiles[0][7]));

        for (int i = 0; i < 8; i++) {
            expected.add(new Pawn(WHITE, tiles[1][i]));
        }

        assertEquals(expected, whitePieceList);
    }

    @Test
    public void moveToEmptyTest() {
        boolean flag = false;
        Board board = new StandardBoard();
        whitePieceList = board.getWhitePieceList();
        Piece movedPiece = whitePieceList.get(0);
        flag = board.move(movedPiece, tiles[3][3]);
        assertEquals(flag, true);
    }

//    @Test
//    public void moveToCaptureTest() {
//        boolean flag = false;
//        Board board = new StandardBoard();
//        blackPieceList = board.getBlackPieceList();
//        Piece movedPiece = blackPieceList.get(0);
//        flag = board.move(movedPiece, tiles[6][6]);
//
//        assertEquals(flag, true);
//    }

    @Test
    public void isCheckMated()
    {
        boolean flag = false;
        Board board = new StandardBoard();
        flag = board.isCheckMated();
        assertEquals(flag,true);
        //flag = board.
    }

}