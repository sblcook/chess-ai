package Board;

import Pieces.Piece;

import Enums.Colors;
import Pieces.Piece;

public class Tile {

    public Tile(int row, int column){
        this.row = row;
        this.column = column;
    }

    public Tile(int row, int column, Piece piece){
        this.row = row;
        this.column = column;
        this.piece = piece;
    }

    private int column;
    private int row;
    private Colors color;
    private Piece piece;

    int getColumn(){
        return this.column;
    }

    int getRow(){
        return this.row;
    }

    public Colors getColor(){
        return this.color;
    }

    void setColor(Colors color){
        this.color = color;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tile tile = (Tile) o;

        if (column != tile.column) return false;
        if (row != tile.row) return false;
        if (color != tile.color) return false;
        return piece != null ? piece.equals(tile.piece) : tile.piece == null;
    }

    @Override
    public int hashCode() {
        int result = column;
        result = 31 * result + row;
        result = 31 * result + (color != null ? color.hashCode() : 0);
        result = 31 * result + (piece != null ? piece.hashCode() : 0);
        return result;
    }
}
