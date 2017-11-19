/*
source: tile
destination: tile
movePiece : Piece
capturedPiece : Piece
 */

import Board.Tile;
import Pieces.Piece;

import static java.awt.Color.black;
import static java.awt.Color.white;

public class Move {
   private Tile source;
   private Tile destination;
   private Piece movedPiece;
   private Piece capturedPiece;

   public Move(Tile source,Tile destination,Piece movedPiece,Piece capturedPiece)
   {
      this.source=source;
      this.destination=destination;
      this.movedPiece=movedPiece;
      this.capturedPiece=capturedPiece;
   }
   //Getters
   public Tile getSource() {
      return source;
   }

   public Tile getDestination() {
      return destination;
   }

   public Piece getMovedPiece() {
      return movedPiece;
   }

   public Piece getCapturedPiece() {
      return capturedPiece;
   }
}
