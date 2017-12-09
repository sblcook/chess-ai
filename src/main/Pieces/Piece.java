package Pieces;

import Board.Tile;
import Enums.Colors;
import Enums.PieceType;

public class Piece {

    private Colors pieceColor;
    private Tile location;
    private PieceType pieceType;

    Piece(Colors color, Tile tile, PieceType pieceType){
        this.pieceColor = color;
        this.location = tile;
        this.pieceType = pieceType;
    }

    public Colors getPieceColor(){
        return this.pieceColor;
    }

    public PieceType getPieceType(){
        return this.pieceType;
    }

    public Tile getLocation(){
        return this.location;
    }

    public void setLocation(Tile location) {
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Piece piece = (Piece) o;

        if (pieceColor != piece.pieceColor) return false;
        if (location != null ? !location.equals(piece.location) : piece.location != null) return false;
        return pieceType == piece.pieceType;
    }

    @Override
    public int hashCode() {
        int result = pieceColor != null ? pieceColor.hashCode() : 0;
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + (pieceType != null ? pieceType.hashCode() : 0);
        return result;
    }
}
