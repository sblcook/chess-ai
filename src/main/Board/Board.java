package Board;

import Pieces.Piece;

import java.util.Arrays;
import java.util.List;

public abstract class Board {

    //Class variables
    protected List<Piece> blackPieceList;
    protected List<Piece> whitePieceList;
    protected List<Piece> removedPieceList;
    protected Tile[][] tiles;

    //Methods
    public List<Piece> getBlackPieceList() {
        return blackPieceList;
    }
    public List<Piece> getWhitePieceList() { return whitePieceList; }
    public List<Piece> getRemovedPieceList() { return removedPieceList;}

    public Tile[][] getTiles() {
        return tiles;
    }

    protected abstract Tile[][] createTiles();

    protected abstract List<Piece> createBlackPieceList(Tile[][] tiles);

    protected abstract List<Piece> createWhitePieceList(Tile[][] tiles);

    protected abstract List<Piece> createRemovedPieceList(Tile[][] tiles);

    protected abstract boolean move(Piece movedPiece, Tile destination);

    protected abstract boolean removePiece(Piece removedPiece);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Board board = (Board) o;

        if (blackPieceList != null ? !blackPieceList.equals(board.blackPieceList) : board.blackPieceList != null)
            return false;
        if (whitePieceList != null ? !whitePieceList.equals(board.whitePieceList) : board.whitePieceList != null)
            return false;
        return Arrays.deepEquals(tiles, board.tiles);
    }

    @Override
    public int hashCode() {
        int result = blackPieceList != null ? blackPieceList.hashCode() : 0;
        result = 31 * result + (whitePieceList != null ? whitePieceList.hashCode() : 0);
        result = 31 * result + Arrays.deepHashCode(tiles);
        return result;
    }
}
