package Board;

public class Tile {

    public Tile(int row, int column){
        this.row = row;
        this.column = column;
    }
    private int column;
    private int row;

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
