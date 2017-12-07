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

public class StandardBoard {

    protected Tile[][] tiles;

    public StandardBoard() {
        tiles = createTiles(); //important that tiles are created first, req for pieceLists
    }

    private Tile[][] createTiles() {
        int i, j;
        Tile[][] tileGrid = new Tile[8][8];
        for (i = 0; i < 8; i++) {
            for (j = 0; j < 8; j++) {
                tileGrid[i][j] = new Tile(i + 1, j + 1); //arrays are zero-indexed
                //we want the board pieces to be standard chess pieces
            }
        }
        return tileGrid;
    }

}
