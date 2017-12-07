package Board;

import Enums.Colors;
import Pieces.Bishop;
import Pieces.King;
import Pieces.Knight;
import Pieces.Pawn;
import Pieces.Piece;
import Pieces.Queen;
import Pieces.Rook;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import static Enums.Colors.BLACK;
import static Enums.Colors.WHITE;

public class Board extends StandardBoard {

    private List<Piece> blackPieceList;
    private List<Piece> whitePieceList;

    public Board(){
        super();
        blackPieceList = createWhitePieceList(tiles);
        whitePieceList = createBlackPieceList(tiles);
    }

    private void createValidMoves(){
        Iterator<Piece> iterator;
        iterator = whitePieceList.iterator(); //only checking white list now, should do black list
        List<Tile> moves = null;

        while(iterator.hasNext()){
            Piece piece = iterator.next();

            switch(piece.getPieceType()){
                case PAWN:
                    moves = pawnMoves(piece);
                    break;
            }
            validMoves.put(piece, moves);
        }
    }

    private List<Tile> pawnMoves(Piece piece){
        List<Tile> moves = new LinkedList<Tile>();
        Tile location = piece.getLocation();

        if(piece.getPieceColor() == BLACK){
            moves.add(tiles[location.getRow()-1][location.getColumn()]);
            if(!piece.hasMoved())
                moves.add(tiles[location.getRow()-2][location.getColumn()]);
        }
        else if (piece.getPieceColor() == WHITE){
            moves.add(tiles[location.getRow()][location.getColumn()+1]);
            if(!piece.hasMoved())
                moves.add(tiles[location.getRow()+2][location.getColumn()]);
        }
        return moves;
    }

    private List<Piece> createBlackPieceList(Tile[][] tiles) {
        List<Piece> pieceList = new LinkedList<>();

        pieceList.add(new Rook(BLACK, tiles[7][0]));
        pieceList.add(new Knight(BLACK, tiles[7][1]));
        pieceList.add(new Bishop(BLACK, tiles[7][2]));
        pieceList.add(new Queen(BLACK, tiles[7][3]));
        pieceList.add(new King(BLACK, tiles[7][4]));
        pieceList.add(new Bishop(BLACK, tiles[7][5]));
        pieceList.add(new Knight(BLACK, tiles[7][6]));
        pieceList.add(new Rook(BLACK, tiles[7][7]));

        for (int i = 0; i < 8; i++) {
            pieceList.add(new Pawn(BLACK, tiles[6][i]));
        }

        return pieceList;
    }

    private List<Piece> createWhitePieceList(Tile[][] tiles) {
        List<Piece> pieceList = new LinkedList<>();

        pieceList.add(new Rook(WHITE, tiles[0][0]));
        pieceList.add(new Knight(WHITE, tiles[0][1]));
        pieceList.add(new Bishop(WHITE, tiles[0][2]));
        pieceList.add(new Queen(WHITE, tiles[0][3]));
        pieceList.add(new King(WHITE, tiles[0][4]));
        pieceList.add(new Bishop(WHITE, tiles[0][5]));
        pieceList.add(new Knight(WHITE, tiles[0][6]));
        pieceList.add(new Rook(WHITE, tiles[0][7]));

        for (int i = 0; i < 8; i++) {
            pieceList.add(new Pawn(WHITE, tiles[1][i]));
        }

        return pieceList;
    }

    public boolean move(Piece movedPiece, Tile destination) {
        boolean flag = false;
        switch (movedPiece.getPieceColor()) {
            case WHITE:
                flag = moveWhitePiece(movedPiece, destination);
                break;
            case BLACK:
                flag = moveBlackPiece(movedPiece, destination);
                break;
        }

        return flag;
    }

    private boolean moveWhitePiece(Piece movedPiece, Tile destination) {
        boolean flag = false;
        if(tileEmpty(destination))
        {
            movedPiece.setLocation(destination);
            flag = true;
        }
        else
        {
            if(tileHasBlackPiece(destination))
            {
                flag = capturePiece(movedPiece,destination);
            }
            else
            {
                //Do nothing because tile already has a white piece on it
            }
        }
        return flag;
    }

    private boolean moveBlackPiece(Piece movedPiece, Tile destination) {
        boolean flag = false;
        if(tileEmpty(destination))
        {
            movedPiece.setLocation(destination);
            flag = true;
        }
        else
        {
            if(tileHasWhitePiece(destination))
            {
                flag = capturePiece(movedPiece,destination);
            }
            else
            {
                //Do nothing because tile already has a white piece on it
            }
        }
        return flag;
    }

    private boolean capturePiece(Piece movedPiece, Tile destination) {
        //should be false unless moveValid
        boolean flag = true;
        /*
        if(moveValid(movedPiece, destination))
        {
            //capture Piece
            flag = true;
        }
        */
        return flag;
    }

    private boolean tileHasBlackPiece(Tile destination) {
        boolean flag = false;
        for (int i = 0; i < blackPieceList.size(); i++) {
            if(destination.equals(blackPieceList.get(i).getLocation()))
            {
                flag = true;
            }
        }
        return flag;
    }

    private boolean tileHasWhitePiece(Tile destination) {
        boolean flag = false;
        for (int i = 0; i < whitePieceList.size(); i++) {
            if(destination.equals(whitePieceList.get(i).getLocation()))
            {
                flag = true;
            }
        }
        return flag;
    }

    private boolean tileEmpty(Tile destination) {
        boolean flag = true;
        for (int i = 0; i < blackPieceList.size(); i++) {
            if(destination.equals(blackPieceList.get(i).getLocation()))
            {
                flag = false;
            }
        }
        for (int i = 0; i < whitePieceList.size(); i++) {
            if(destination.equals(whitePieceList.get(i).getLocation()))
            {
                flag = false;
            }
        }
        return flag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Board board = (Board) o;

        if (blackPieceList != null ? !blackPieceList.equals(board.blackPieceList) : board.blackPieceList != null)
            return false;
        return whitePieceList != null ? whitePieceList.equals(board.whitePieceList) : board.whitePieceList == null;
    }

    @Override
    public int hashCode() {
        int result = blackPieceList != null ? blackPieceList.hashCode() : 0;
        result = 31 * result + (whitePieceList != null ? whitePieceList.hashCode() : 0);
        return result;
    }
}
