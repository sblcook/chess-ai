package Board;

import Enums.Colors;
import Pieces.Bishop;
import Pieces.King;
import Pieces.Knight;
import Pieces.Pawn;
import Pieces.Piece;
import Pieces.Queen;
import Pieces.Rook;

import java.util.LinkedList;
import java.util.List;

public class StandardBoard extends Board {

    private Colors pieceColor;

    public StandardBoard() {
        tiles = createTiles(); //important that tiles are created first, req for pieceLists
        blackPieceList = createBlackPieceList(tiles);
        whitePieceList = createWhitePieceList(tiles);
        removedPieceList = createRemovedPieceList(tiles);
    }


    @Override
    protected Tile[][] createTiles() {
        int i, j;
        Tile[][] tileGrid = new Tile[8][8];
        for (i = 0; i < 8; i++) {
            for (j = 0; j < 8; j++) {
                tileGrid[i][j] = new Tile(i + 1, j + 1); //arrays are zero-indexed
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
    protected List<Piece> createRemovedPieceList(Tile[][] tiles) {
        List<Piece> pieceList = new LinkedList<>();
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

    @Override
    protected boolean removePiece(Piece removedPiece) {
        boolean flag = false;
            switch (removedPiece.getPieceColor()) {
                case WHITE:
                    flag = removeWhitePiece(removedPiece);
                    break;
                case BLACK:
                    flag = removeBlackPiece(removedPiece);
                    break;
        }
        return flag;
    }

    private boolean removeWhitePiece(Piece removedPiece)
    {
        boolean flag = false;

        for (int j = 0; j < whitePieceList.size(); j++) {
            if(removedPiece.equals(whitePieceList.get(j)))
            {
                whitePieceList.remove(removedPiece);
                removedPieceList.add(removedPiece);
                flag = true;
            }
        }
        return flag;
    }

    private boolean removeBlackPiece(Piece removedPiece)
    {
        boolean flag = false;
        for (int i = 0; i < blackPieceList.size(); i++) {
            if (removedPiece.equals(blackPieceList.get(i)))
            {
                blackPieceList.remove(removedPiece);
                removedPieceList.add(removedPiece);
                flag = true;
            }
        }
        return flag;
    }

    private boolean moveWhitePiece(Piece movedPiece, Tile destination) {
        boolean flag = false;
        if (tileEmpty(destination)) {
            movedPiece.setLocation(destination);
            flag = true;
        } else {
            if (tileHasBlackPiece(destination)) {
                flag = capturePiece(movedPiece, destination);
            } else {
                //Do nothing because tile already has a white piece on it
            }
        }
        return flag;
    }

    private boolean moveBlackPiece(Piece movedPiece, Tile destination) {
        boolean flag = false;
        if (tileEmpty(destination)) {
            movedPiece.setLocation(destination);
            flag = true;
        } else {
            if (tileHasWhitePiece(destination)) {
                flag = capturePiece(movedPiece, destination);
            } else {
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
            if (destination.equals(blackPieceList.get(i).getLocation())) {
                flag = true;
            }
        }
        return flag;
    }

    private boolean tileHasWhitePiece(Tile destination) {
        boolean flag = false;
        for (int i = 0; i < whitePieceList.size(); i++) {
            if (destination.equals(whitePieceList.get(i).getLocation())) {
                flag = true;
            }
        }
        return flag;
    }

    private boolean tileEmpty(Tile destination) {
        boolean flag = true;
        for (int i = 0; i < blackPieceList.size(); i++) {
            if (destination.equals(blackPieceList.get(i).getLocation())) {
                flag = false;
            }
        }
        for (int i = 0; i < whitePieceList.size(); i++) {
            if (destination.equals(whitePieceList.get(i).getLocation())) {
                flag = false;
            }
        }
        return flag;
    }
}
