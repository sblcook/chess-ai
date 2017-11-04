package Pieces;

import Board.Tile;
import Enums.Colors;
import Enums.PieceType;

public class King extends Piece {

    King(Colors color, Tile location){
        super(color, location, PieceType.KING);
    }
}
