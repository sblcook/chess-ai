package Pieces;

import Board.Tile;
import Enums.Colors;
import Enums.PieceType;

class Piece {

    private Colors pieceColor;
    private Tile location;
    private PieceType pieceType;

    Piece(Colors color, Tile tile, PieceType pieceType){
        this.pieceColor = color;
        this.location = tile;
        this.pieceType = pieceType;
    }

    Colors getPieceColor(){
        return this.pieceColor;
    }

    PieceType getPieceType(){
        return this.pieceType;
    }

    Tile getLocation(){
        return this.location;
    }

    public void setLocation(Tile location) {
        this.location = location;
    }
}
