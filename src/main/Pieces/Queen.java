package Pieces;

import Board.Tile;
import Enums.Colors;
import Enums.PieceType;

public class Queen extends Piece {

    Queen(Colors color, Tile tile){
        super(color, tile, PieceType.QUEEN);
    }
}
