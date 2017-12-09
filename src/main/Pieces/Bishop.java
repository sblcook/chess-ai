package Pieces;

import Board.Tile;
import Enums.Colors;
import Enums.PieceType;

public class Bishop extends Piece{

    public Bishop(Colors color, Tile tile){
        super(color, tile, PieceType.BISHOP);
    }
}
