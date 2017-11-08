package Pieces;

import Board.Tile;
import Enums.Colors;
import Enums.PieceType;

public class Queen extends Piece {

    public Queen(Colors color, Tile tile){
        super(color, tile, PieceType.QUEEN);
    }
}
