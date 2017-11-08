package Board;

import Pieces.Piece;

import java.util.List;

public abstract class Board {

    //attempting to implement a Factory Method

    private Tile[][] tiles;
    private List<Piece> blackPieceList;
    private List<Piece> whitePieceList;

    public Board(){
        tiles = createTiles(); //important that tiles are created first, req for pieceLists
        blackPieceList = createWhitePieceList(tiles);
        whitePieceList = createBlackPieceList(tiles);
    }

    abstract protected Tile[][] createTiles();

    abstract protected List<Piece> createBlackPieceList(Tile[][] tiles);

    abstract protected List<Piece> createWhitePieceList(Tile[][] tiles);
}
