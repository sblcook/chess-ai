package Pieces;

import Board.Tile;
import Enums.Colors;
import Enums.PieceType;

public class Knight extends Piece{

    Knight(Colors color, Tile tile){
        super(color, tile, PieceType.KNIGHT);
    }
}
