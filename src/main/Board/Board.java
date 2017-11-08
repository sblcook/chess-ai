package Board;

import Enums.Colors;
import Pieces.Piece;

import java.util.List;

public abstract class Board {

    //attempting to implement a Factory Method

    Tile[][] tiles;
    List<? extends Piece> blackPieceList;
    List<? extends Piece> whitePieceList;

    Board(){
        tiles = createTiles();
        blackPieceList = createPieceList(Colors.BLACK);
        whitePieceList = createPieceList(Colors.WHITE);
    }

    abstract protected Tile[][] createTiles();

    abstract protected List<? extends Piece> createPieceList(Colors pieceColor);
}
