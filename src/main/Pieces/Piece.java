package Pieces;

import Board.Tile;
import Enums.Colors;

class Piece {

    private Colors pieceColor;
    private Tile location;

    Piece(Colors color, Tile tile){
        this.pieceColor = color;
        this.location = tile;
    }

    Colors getPieceColor(){
        return this.pieceColor;
    }

    Tile getLocation(){
        return this.location;
    }

    public void setLocation(Tile location) {
        this.location = location;
    }
}
