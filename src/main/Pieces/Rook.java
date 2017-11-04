package Pieces;

import Board.Tile;
import Enums.Colors;
import Enums.PieceType;

public class Rook extends Piece {

    Rook(Colors colors, Tile tile){
        super(colors, tile, PieceType.ROOK);
    }
}
