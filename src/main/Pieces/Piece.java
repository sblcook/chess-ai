package Pieces;

import Enums.Colors;

class Piece {

    private Colors pieceColor;

    Piece(Colors color){
        this.pieceColor = color;
    }

    Colors getPieceColor(){
        return pieceColor;
    }
}
