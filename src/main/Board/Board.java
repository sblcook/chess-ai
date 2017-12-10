package Board;

import Move.Move;
import Pieces.Piece;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public abstract class Board {

    //Class variables
    protected List<Piece> blackPieceList;
    protected List<Piece> whitePieceList;
    protected List<Piece> removedPieceList;
    protected Tile[][] tiles;
    protected Map<Piece, List<Tile>> validMoves;

    //Methods
    public List<Piece> getBlackPieceList() {
        return blackPieceList;
    }
    public List<Piece> getWhitePieceList() { return whitePieceList; }
    public List<Piece> getRemovedPieceList() { return removedPieceList;}
    protected abstract Move move(Piece movedPiece, Tile destination);
    public abstract boolean isCheckMated();
    public Tile[][] getTiles() {
        return tiles;
    }

    public Map<Piece, List<Tile>> getValidMoves() {
        return validMoves;
    }

    protected abstract Tile[][] createTiles();

    protected abstract List<Piece> createBlackPieceList(Tile[][] tiles);

    protected abstract List<Piece> createWhitePieceList(Tile[][] tiles);

    protected abstract List<Piece> createRemovedPieceList(Tile[][] tiles);

    protected abstract boolean removePiece(Piece removedPiece);

    protected void setValidMoves(Map<Piece, List<Tile>> validMoves) {
        this.validMoves = validMoves;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Board board = (Board) o;

        if (blackPieceList != null ? !blackPieceList.equals(board.blackPieceList) : board.blackPieceList != null)
            return false;
        if (whitePieceList != null ? !whitePieceList.equals(board.whitePieceList) : board.whitePieceList != null)
            return false;
        if (removedPieceList != null ? !removedPieceList.equals(board.removedPieceList) : board.removedPieceList != null)
            return false;
        if (!Arrays.deepEquals(tiles, board.tiles)) return false;
        return validMoves != null ? validMoves.equals(board.validMoves) : board.validMoves == null;
    }

    @Override
    public int hashCode() {
        int result = blackPieceList != null ? blackPieceList.hashCode() : 0;
        result = 31 * result + (whitePieceList != null ? whitePieceList.hashCode() : 0);
        result = 31 * result + (removedPieceList != null ? removedPieceList.hashCode() : 0);
        result = 31 * result + Arrays.deepHashCode(tiles);
        result = 31 * result + (validMoves != null ? validMoves.hashCode() : 0);
        return result;
    }
}
