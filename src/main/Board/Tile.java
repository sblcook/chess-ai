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

}
