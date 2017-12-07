package Board;

import Enums.Colors;
import Pieces.Bishop;
import Pieces.King;
import Pieces.Knight;
import Pieces.Pawn;
import Pieces.Piece;
import Pieces.Queen;
import Pieces.Rook;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static Enums.Colors.BLACK;
import static Enums.Colors.WHITE;

public class StandardBoard {

    protected Tile[][] tiles;
    protected Map<Piece, List<Tile>> validMoves;

    public StandardBoard() {
        tiles = createTiles(); //important that tiles are created first, req for pieceLists
    }

    private Tile[][] createTiles() {
        int i, j;
        Tile[][] tileGrid = new Tile[8][8];
        for (i = 0; i < 8; i++) {
            for (j = 0; j < 8; j++) {
                tileGrid[i][j] = new Tile(i + 1, j + 1); //arrays are zero-indexed
                if((i+j)%2 == 0){
                    tileGrid[i][j].setColor(BLACK);
                }
                else{
                    tileGrid[i][j].setColor(WHITE);
                }
                //we want the board pieces to be standard chess pieces
            }
        }
        return tileGrid;
    }


}
