package Board;

import Enums.Colors;
import Pieces.Bishop;
import Pieces.King;
import Pieces.Knight;
import Pieces.Pawn;
import Pieces.Piece;
import Pieces.Queen;
import Pieces.Rook;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import static Enums.Colors.BLACK;
import static Enums.Colors.WHITE;

public class StandardBoard extends Board{

    public StandardBoard() {
        tiles = createTiles(); //important that tiles are created first, req for pieceLists
        blackPieceList = createWhitePieceList(tiles);
        whitePieceList = createBlackPieceList(tiles);
    }

    public boolean isValidMove(Piece piece, Tile tile){
        switch(piece.getPieceType()){
            case PAWN:
                return checkPawnMove(piece, tile);
            case ROOK:
                return checkRookMove(piece, tile);
            default:
                return false;
        }
    }

    private boolean checkRookMove(Piece piece, Tile tile){

        return false;
    }

    private boolean isClearPath(Tile source, Tile dest){
        if(source.equals(dest))
            return false; //same location

        if(source.getRow() == dest.getRow() && source.getColumn() != dest.getColumn()){ //same row, dif cols
            int lower = Math.min(source.getColumn(), dest.getColumn());
            int higher = Math.max(source.getColumn(), dest.getColumn());

            for (int i = lower + 1; i < higher; i++){
                if(tiles[dest.getRow()][i].getPiece() != null)
                    return false;
            }
        }
        else if(source.getColumn() == dest.getColumn() && source.getRow() != dest.getRow()){ //same col, diff rows
            int lower = Math.min(source.getRow(), dest.getRow());
            int higher = Math.max(source.getRow(), dest.getRow());

            for(int i = lower + 1; i < higher; i++){
                if(tiles[i][dest.getColumn()].getPiece() != null)
                    return false;
            }
        }

        else if(Math.abs(source.getColumn() - dest.getColumn()) == Math.abs(source.getRow() - dest.getRow())) { //diagonal

            int lowCol = Math.min(source.getColumn(), dest.getColumn());
            int hiCol = Math.max(source.getColumn(), dest.getColumn());
            int lowRow = Math.min(source.getRow(), dest.getRow());
            int hiRow = Math.max(source.getRow(), dest.getRow());

            if(source.getColumn() + source.getRow() == dest.getColumn() + dest.getRow()){//on the NW-SE diagonal
                //work from NW to SE
                int i = hiRow - 1; //going down rows
                int j = lowCol + 1; //going up cols

                while(i > lowRow && j < hiCol){ //in range
                    if(tiles[i][j].getPiece() != null)
                        return false;
                    i -= 1;
                    j += 1;
                }
            }
            else { //on the NE-SW diagonal
                //work from SW to NE
                int i = lowRow + 1;
                int j = lowCol + 1;

                while(i < hiRow && j < hiCol){ //see if path is free
                    if(tiles[i][j].getPiece() != null)
                        return false;

                    i+= 1;
                    j+= 1;
                }
            }
        }

        return true;
    }

    //still need to check that its on the board or the same location and clear path
    private boolean checkPawnMove(Piece piece, Tile tile){

        if(!isClearPath(piece.getLocation(), tile)) { //blocked path
            return false;
        }
        if(piece.getLocation().equals(tile)) { //moving to the place where the piece already is
            return false;
        }

        if(tile.getColumn() == piece.getLocation().getColumn() && tile.getPiece() == null){ //same column, forward move,
            // no piece already there
            if(piece.getPieceColor() == BLACK) { //can only move down
                int diff = piece.getLocation().getRow() - tile.getRow();
                if(!piece.hasMoved()){ //pawn can move forward 2
                    if (diff == 2 || diff == 1) {
                        return true;
                    }
                }
                else{
                    if(diff == 1){
                        return true;
                    }
                }
            }
            else if(piece.getPieceColor() == WHITE){ //can only move up
                int diff = piece.getLocation().getRow() - tile.getRow();
                if(!piece.hasMoved()){ //pawn can move forward 2
                    if (diff == -2 || diff == -1) {
                        return true;
                    }
                }
                else{
                    if(diff == -1){
                        return true;
                    }
                }
            }

        } else if(tile.getPiece() != null) { //theres a piece to take
            if (Math.abs(tile.getColumn() - piece.getLocation().getColumn()) % 8 == 1) { //1 column apart, could attack
                if (piece.getPieceColor() == BLACK) {//move down
                    if ((piece.getLocation().getRow() - tile.getRow()) == 1) { //tile is row in front of piece
                        if (tile.getPiece().getPieceColor() == WHITE) { //there is a piece to take
                            return true;
                        }
                    }
                } else if (piece.getPieceColor() == WHITE) {//move up
                    if ((tile.getRow() - piece.getLocation().getRow()) == 1) {//tile is row in front of piece
                        if (tiles[tile.getRow()][tile.getColumn()].getPiece().getPieceColor() == BLACK) { //there is a piece to take
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    @Override
    protected Tile[][] createTiles() {
        int i, j;
        Tile[][] tileGrid = new Tile[8][8];
        for (i = 0; i < 8; i++) {
            for (j = 0; j < 8; j++) {
                tileGrid[i][j] = new Tile(i + 1, j + 1); //arrays are zero-indexed
                if((i+j)%2 == 0){
                    tileGrid[i][j].setColor(BLACK);
                }
                else{
                    tileGrid[i][j].setColor(WHITE);
                }
                //we want the board pieces to be standard chess pieces
            }
        }
        return tileGrid;
    }

    @Override
    protected List<Piece> createBlackPieceList(Tile[][] tiles) {
        List<Piece> pieceList = new LinkedList<>();

        pieceList.add(new Rook(Colors.BLACK, tiles[7][0]));
        pieceList.add(new Knight(Colors.BLACK, tiles[7][1]));
        pieceList.add(new Bishop(Colors.BLACK, tiles[7][2]));
        pieceList.add(new Queen(Colors.BLACK, tiles[7][3]));
        pieceList.add(new King(Colors.BLACK, tiles[7][4]));
        pieceList.add(new Bishop(Colors.BLACK, tiles[7][5]));
        pieceList.add(new Knight(Colors.BLACK, tiles[7][6]));
        pieceList.add(new Rook(Colors.BLACK, tiles[7][7]));

        for (int i = 0; i < 8; i++) {
            pieceList.add(new Pawn(Colors.BLACK, tiles[6][i]));
        }

        return pieceList;
    }

    @Override
    protected List<Piece> createWhitePieceList(Tile[][] tiles) {
        List<Piece> pieceList = new LinkedList<>();

        pieceList.add(new Rook(Colors.WHITE, tiles[0][0]));
        pieceList.add(new Knight(Colors.WHITE, tiles[0][1]));
        pieceList.add(new Bishop(Colors.WHITE, tiles[0][2]));
        pieceList.add(new Queen(Colors.WHITE, tiles[0][3]));
        pieceList.add(new King(Colors.WHITE, tiles[0][4]));
        pieceList.add(new Bishop(Colors.WHITE, tiles[0][5]));
        pieceList.add(new Knight(Colors.WHITE, tiles[0][6]));
        pieceList.add(new Rook(Colors.WHITE, tiles[0][7]));

        for (int i = 0; i < 8; i++) {
            pieceList.add(new Pawn(Colors.WHITE, tiles[1][i]));
        }

        return pieceList;
    }

    @Override
    protected boolean move(Piece movedPiece, Tile destination) {
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
        if (!super.equals(o)) return false;

        StandardBoard that = (StandardBoard) o;

        if (!Arrays.deepEquals(tiles, that.tiles)) return false;
        return validMoves != null ? validMoves.equals(that.validMoves) : that.validMoves == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + Arrays.deepHashCode(tiles);
        result = 31 * result + (validMoves != null ? validMoves.hashCode() : 0);
        return result;
    }
}
