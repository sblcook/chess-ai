package Board;


import Pieces.Piece;

public class Tile {

    private int column;
    private int row;

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    private Piece piece;



    public Tile(int row, int column, Piece piece){
        this.row = row;
        this.column = column;
        this.piece = piece;


    }



    public Piece getPiece() {
        return piece;
    }






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
