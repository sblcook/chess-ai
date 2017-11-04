package Pieces;

import Board.Tile;
import Enums.Colors;
import Enums.PieceType;

public class Pawn extends Piece{

    Pawn(Colors colors, Tile tile){
        super(colors, tile, PieceType.PAWN);
    }
}
