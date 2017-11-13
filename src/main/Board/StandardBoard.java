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

public class StandardBoard extends Board{


    public StandardBoard(){
        tiles = createTiles(); //important that tiles are created first, req for pieceLists
        blackPieceList = createWhitePieceList(tiles);
        whitePieceList = createBlackPieceList(tiles);
    }
    @Override
    protected Tile[][] createTiles(){
        int i, j;
        Tile[][] tileGrid = new Tile[8][8];
        for (i=0; i < 8; i++){
            for(j=0; j < 8; j++){
                tileGrid[i][j] = new Tile(i+1, j+1); //arrays are zero-indexed
                //we want the board pieces to be standard chess pieces
            }
        }
        return tileGrid;
    }

    @Override
    protected List<Piece> createBlackPieceList(Tile[][] tiles){
        List<Piece> pieceList = new LinkedList<>();

        pieceList.add(new Rook(Colors.BLACK, tiles[7][0]));
        pieceList.add(new Knight(Colors.BLACK, tiles[7][1]));
        pieceList.add(new Bishop(Colors.BLACK, tiles[7][2]));
        pieceList.add(new Queen(Colors.BLACK, tiles[7][3]));
        pieceList.add(new King(Colors.BLACK, tiles[7][4]));
        pieceList.add(new Bishop(Colors.BLACK, tiles[7][5]));
        pieceList.add(new Knight(Colors.BLACK, tiles[7][6]));
        pieceList.add(new Rook(Colors.BLACK, tiles[7][7]));

        for(int i = 0; i < 8; i++){
            pieceList.add(new Pawn(Colors.BLACK, tiles[6][i]));
        }

        return pieceList;
    }

    @Override
    protected List<Piece> createWhitePieceList(Tile[][] tiles){
        List<Piece> pieceList = new LinkedList<>();

        pieceList.add(new Rook(Colors.WHITE, tiles[0][0]));
        pieceList.add(new Knight(Colors.WHITE, tiles[0][1]));
        pieceList.add(new Bishop(Colors.WHITE, tiles[0][2]));
        pieceList.add(new Queen(Colors.WHITE, tiles[0][3]));
        pieceList.add(new King(Colors.WHITE, tiles[0][4]));
        pieceList.add(new Bishop(Colors.WHITE, tiles[0][5]));
        pieceList.add(new Knight(Colors.WHITE, tiles[0][6]));
        pieceList.add(new Rook(Colors.WHITE, tiles[0][7]));

        for(int i = 0; i < 8; i++){
            pieceList.add(new Pawn(Colors.WHITE, tiles[1][i]));
        }

        return pieceList;
    }
}
