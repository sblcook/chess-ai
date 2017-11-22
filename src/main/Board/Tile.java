package Board;

import Pieces.Piece;

public class Tile {

    public Tile(int row, int column, Enum tileColor, boolean isOccupied, Piece occupyingPiece){
        this.row = row;
        this.column = column;
        this.tileColor = tileColor;
        this.isOccupied = isOccupied;
        this. occupyingPiece = occupyingPiece;

    }
    private int column;
    private int row;
    public Enum tileColor;
    public boolean isOccupied;
    public Piece occupyingPiece;




    int getColumn(){
        return this.column;
    }

    int getRow(){
        return this.row;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tile tile = (Tile) o;

        return row == tile.row && column == tile.column;
    }

    @Override
    public int hashCode() {
        int result = column;
        result = 31 * result + row;
        return result;
    }

}
