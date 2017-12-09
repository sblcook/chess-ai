package Board;

import Enums.Colors;
import Enums.PieceType;
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
        setTiles(tiles);
    }


    @Override
    protected Tile[][] createTiles() {
        int i, j;
        Tile[][] tileGrid = new Tile[8][8];
        for (i = 0; i < 8; i++) {
            for (j = 0; j < 8; j++) {
                tileGrid[i][j] = new Tile(i + 1, j + 1, null); //arrays are zero-indexed
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

    @Override
    public boolean isCheckMated() {
        boolean flag = false;
        move(whitePieceList.get(6), tiles[5][5]);
        if (isInCheck()) {
            flag = checkForCheckMate();
        } else {
            flag = false;
        }
        //flag = checkForCheckMate();
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

    private void setTiles(Tile[][] tiles) {
        int i, j;
        int counter = 0;
        for (i = 0; i < 2; i++) {
            for (j = 0; j < 8; j++) {
                tiles[i][j].setPiece(whitePieceList.get(counter++));
            }
        }
        counter = 0;
        for (i = 6; i < 8; i++) {
            for (j = 0; j < 8; j++) {
                tiles[i][j].setPiece(blackPieceList.get(counter++));
            }
        }
    }


    private boolean removeWhitePiece(Piece removedPiece) {
        boolean flag = false;

        for (int j = 0; j < whitePieceList.size(); j++) {
            if (removedPiece.equals(whitePieceList.get(j))) {
                whitePieceList.remove(removedPiece);
                removedPieceList.add(removedPiece);
                flag = true;
            }
        }
        return flag;
    }

    private boolean removeBlackPiece(Piece removedPiece) {
        boolean flag = false;
        for (int i = 0; i < blackPieceList.size(); i++) {
            if (removedPiece.equals(blackPieceList.get(i))) {
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
            tiles[movedPiece.getLocation().getRow() - 1][movedPiece.getLocation().getColumn() - 1].setPiece(null);
            movedPiece.setLocation(destination);
            tiles[movedPiece.getLocation().getRow() - 1][movedPiece.getLocation().getColumn() - 1].setPiece(movedPiece);
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
            tiles[movedPiece.getLocation().getRow() - 1][movedPiece.getLocation().getColumn() - 1].setPiece(null);
            movedPiece.setLocation(destination);
            tiles[movedPiece.getLocation().getRow() - 1][movedPiece.getLocation().getColumn() - 1].setPiece(movedPiece);
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


    private boolean isInCheck() {
        boolean flag = false;
        int i = 0;
        flag = onThreatenedTile(blackPieceList.get(4));
        return flag;
    }

    private boolean checkForCheckMate() {
        boolean flag = false;
        //check if piece
        Piece threatenedPiece = blackPieceList.get(4);
        flag = onThreatenedTile(threatenedPiece);
        return flag;
    }


    private boolean onThreatenedTile(Piece piece) {
        int counter = 0;
        int i = 0;
        int j = 0;
        counter += checkDiagNear(piece);
        counter += checkDiagFar(piece);
        counter += checkHorNear(piece);
        counter += checkHorFar(piece);
        counter += checkVertNear(piece);
        counter += checkVertFar(piece);
        if (piece.getPieceColor().equals(Colors.BLACK)) {
            counter += checkForWhiteKnights(piece);
        }
        if (piece.getPieceColor().equals(Colors.WHITE)) {
            counter += checkForBlackKnights(piece);
        }
        if (counter > 0) {
            return true;
        } else {
            return false;
        }
    }

    /*
    *
    * BEGINNING OF CHECKING CODE
    *
     */

    //check diag near
    private int checkDiagNear(Piece piece) {
        int i = 0;
        int j = 0;
        int counter = 0;
        int nearBoundsRight = piece.getLocation().getRow() + 1;
        int nearBoundsLeft = piece.getLocation().getRow() - 2;
        int nearBoundsUp = piece.getLocation().getColumn() + 1;
        int nearBoundsDown = piece.getLocation().getColumn() - 2;

        nearBoundsRight = (nearBoundsRight > 7) ? 7 : nearBoundsRight;
        nearBoundsLeft = (nearBoundsLeft < 0) ? 0 : nearBoundsLeft;
        nearBoundsUp = (nearBoundsUp > 7) ? 7 : nearBoundsUp;
        nearBoundsDown = (nearBoundsDown < 0) ? 0 : nearBoundsDown;
        int x = piece.getLocation().getRow() - 1;
        int y = piece.getLocation().getColumn() - 1;
        j = y;
        //check upper right diagonal
        for (i = x + 1; i < nearBoundsRight; i++) {
            if (j < nearBoundsUp) {
                j++;
            }
            if (tiles[i][j].getPiece() != null) {
                if (tiles[i][j].getPiece().getPieceColor().equals(piece.getPieceColor()) == true) {
                    //break out of for loops
                    i = 9;
                    j = 9;
                } else {
                    if (tiles[i][j].getPiece().getPieceType().equals(PieceType.QUEEN)) {
                        counter = 1;
                    }
                    if (tiles[i][j].getPiece().getPieceType().equals(PieceType.BISHOP)) {
                        counter = 1;
                    }
                    if (tiles[i][j].getPiece().getPieceType().equals(PieceType.PAWN) && tiles[i][j].getPiece().getPieceColor().equals(Colors.WHITE)) {
                        counter = 1;
                    }
                    if (tiles[i][j].getPiece().getPieceType().equals(PieceType.KING)) {
                        counter = 1;
                    }
                }
            } else {
                //do nothing
            }
        }
        //check lower right diagonal
        j = y;
        for (i = x + 1; i < nearBoundsRight; i++) {
            if (j > nearBoundsDown) {
                j--;
            }
            if (tiles[i][j].getPiece() != null) {
                if (tiles[i][j].getPiece().getPieceColor().equals(piece.getPieceColor()) == true) {
                    //break out of for loops
                    i = 9;
                    j = (-1);
                } else {
                    if (tiles[i][j].getPiece().getPieceType().equals(PieceType.QUEEN)) {
                        counter = 1;
                    }
                    if (tiles[i][j].getPiece().getPieceType().equals(PieceType.BISHOP)) {
                        counter = 1;
                    }
                    if (tiles[i][j].getPiece().getPieceType().equals(PieceType.PAWN) && tiles[i][j].getPiece().getPieceColor().equals(Colors.BLACK)) {
                        counter = 1;
                    }
                    if (tiles[i][j].getPiece().getPieceType().equals(PieceType.KING)) {
                        counter = 1;
                    }
                }
            } else {
                //do nothing
            }
        }
        //check upper left diagonal
        j = y;
        for (i = x - 1; i >= nearBoundsLeft; i--) {
            if (j < nearBoundsUp) {
                j++;
            }
            if (tiles[i][j].getPiece() != null) {
                if (tiles[i][j].getPiece().getPieceColor().equals(piece.getPieceColor()) == true) {
                    //break out of for loops
                    i = (-1);
                    j = 9;
                } else {
                    if (tiles[i][j].getPiece().getPieceType().equals(PieceType.QUEEN)) {
                        counter = 1;
                    }
                    if (tiles[i][j].getPiece().getPieceType().equals(PieceType.BISHOP)) {
                        counter = 1;
                    }
                    if (tiles[i][j].getPiece().getPieceType().equals(PieceType.PAWN) && tiles[i][j].getPiece().getPieceColor().equals(Colors.WHITE)) {
                        counter = 1;
                    }
                    if (tiles[i][j].getPiece().getPieceType().equals(PieceType.KING)) {
                        counter = 1;
                    }
                }
            } else {
                //do nothing
            }
        }
        j = y;
        //check lower left diagonal
        for (i = x - 1; i >= nearBoundsLeft; i--) {
            if (j > nearBoundsDown) {
                j--;
            }
            if (tiles[i][j].getPiece() != null) {
                if (tiles[i][j].getPiece().getPieceColor().equals(piece.getPieceColor()) == true) {
                    //break out of for loops
                    i = (-1);
                    j = (-1);
                } else {
                    if (tiles[i][j].getPiece().getPieceType().equals(PieceType.QUEEN)) {
                        counter = 1;
                    }
                    if (tiles[i][j].getPiece().getPieceType().equals(PieceType.BISHOP)) {
                        counter = 1;
                    }
                    if (tiles[i][j].getPiece().getPieceType().equals(PieceType.PAWN) && tiles[i][j].getPiece().getPieceColor().equals(Colors.BLACK)) {
                        counter = 1;
                    }
                    if (tiles[i][j].getPiece().getPieceType().equals(PieceType.KING)) {
                        counter = 1;
                    }
                }
            } else {
                //do nothing
            }
        }
        System.out.println("diagNear " + counter);
        return counter;
    }

    private int checkDiagFar(Piece piece) {
        int i = 0;
        int j = 0;
        boolean flag = false;
        int counter = 0;
        int x = piece.getLocation().getRow() - 1;
        int y = piece.getLocation().getColumn() - 1;
        //check upper right diagonal
        j = y;
        for (i = x + 1; i < 8; i++) {
            if (j < 8) {
                j++;
            }
            if (tiles[i][j].getPiece() != null) {
                if (tiles[i][j].getPiece().getPieceColor().equals(piece.getPieceColor()) == true) {
                    //break out of for loops
                    i = 9;
                } else {
                    if (!tiles[i][j].getPiece().getPieceType().equals(PieceType.QUEEN) && !tiles[i][j].getPiece().getPieceType().equals(PieceType.BISHOP)) {
                        //break out of for loops
                        flag = true;
                        break;
                    }
                    if (tiles[i][j].getPiece().getPieceType().equals(PieceType.QUEEN) && !flag) {
                        counter = 1;
                    }
                    if (tiles[i][j].getPiece().getPieceType().equals(PieceType.BISHOP) && !flag) {
                        counter = 1;
                    }
                }
            } else {
                //do nothing
            }
        }
        //check lower right diagonal
        flag = false;
        j = y;
        for (i = x + 1; i < 8; i++) {
            if (j > (0)) {
                j--;
            }
            if (tiles[i][j].getPiece() != null) {
                if (tiles[i][j].getPiece().getPieceColor().equals(piece.getPieceColor()) == true) {
                    //break out of for loops
                    i = 9;
                } else {
                    if (!tiles[i][j].getPiece().getPieceType().equals(PieceType.QUEEN) && !tiles[i][j].getPiece().getPieceType().equals(PieceType.BISHOP)) {
                        //break out of for loops
                        flag = true;
                        break;
                    }
                    if (tiles[i][j].getPiece().getPieceType().equals(PieceType.QUEEN) && !flag) {
                        counter = 1;
                    }
                    if (tiles[i][j].getPiece().getPieceType().equals(PieceType.BISHOP) && !flag) {
                        counter = 1;
                    }
                }
            } else {
                //do nothing
            }
        }
        //check upper left diagonal
        flag = false;
        y = j;
        for (i = x - 1; i >= 0; i--) {
            if (j < 8) {
                j++;
            }
            if (tiles[i][j].getPiece() != null) {
                if (tiles[i][j].getPiece().getPieceColor().equals(piece.getPieceColor()) == true) {
                    //break out of for loops
                    i = (-1);
                } else {
                    if (!tiles[i][j].getPiece().getPieceType().equals(PieceType.QUEEN) && !tiles[i][j].getPiece().getPieceType().equals(PieceType.BISHOP)) {
                        //break out of for loops
                        flag = true;
                        break;
                    }
                    if (tiles[i][j].getPiece().getPieceType().equals(PieceType.QUEEN) && !flag) {
                        counter = 1;
                    }
                    if (tiles[i][j].getPiece().getPieceType().equals(PieceType.BISHOP) && !flag) {
                        counter = 1;
                    }
                }
            } else {
                //do nothing
            }
        }
        //check lower left diagonal
        flag = false;
        j = y;
        for (i = x - 1; i >= 0; i--) {
            if (j > 0) {
                j--;
            }
            if (tiles[i][j].getPiece() != null) {
                if (tiles[i][j].getPiece().getPieceColor().equals(piece.getPieceColor()) == true) {
                    //break out of for loops
                    i = (-1);
                } else {
                    if (!tiles[i][j].getPiece().getPieceType().equals(PieceType.QUEEN) && !tiles[i][j].getPiece().getPieceType().equals(PieceType.BISHOP)) {
                        //break out of for loops
                        flag = true;
                        break;
                    }
                    if (tiles[i][j].getPiece().getPieceType().equals(PieceType.QUEEN) && !flag) {
                        counter = 1;
                    }
                    if (tiles[i][j].getPiece().getPieceType().equals(PieceType.BISHOP) && !flag) {
                        counter = 1;
                    }
                }
            } else {
                //do nothing
            }
        }
        System.out.println("diagFar " + counter);
        return counter;
    }

    private int checkHorNear(Piece piece) {
        int i = 0;
        int j = 0;
        int counter = 0;
        int nearBoundsRight = piece.getLocation().getRow() + 1;
        int nearBoundsLeft = piece.getLocation().getRow() - 2;
        nearBoundsRight = (nearBoundsRight > 7) ? 7 : nearBoundsRight;
        nearBoundsLeft = (nearBoundsLeft < 0) ? 0 : nearBoundsLeft;
        int x = piece.getLocation().getRow() - 1;
        int y = piece.getLocation().getColumn() - 1;
        //check horizontal to the right
        for (i = x + 1; i < nearBoundsRight; i++) {
            j = y;
            if (tiles[i][j].getPiece() != null) {
                if (tiles[i][j].getPiece().getPieceColor().equals(piece.getPieceColor()) == true) {
                    //break out of for loops
                    i = 9;
                } else {
                    if (tiles[i][j].getPiece().getPieceType().equals(PieceType.QUEEN)) {
                        counter = 1;
                    }
                    if (tiles[i][j].getPiece().getPieceType().equals(PieceType.ROOK)) {
                        counter = 1;
                    }
                    if (tiles[i][j].getPiece().getPieceType().equals(PieceType.KING)) {
                        counter = 1;
                    }
                }
            } else {
                //do nothing
            }
        }
        //check horizontal left
        for (i = x - 1; i >= nearBoundsLeft; i--) {
            j = y;
            if (tiles[i][j].getPiece() != null) {
                if (tiles[i][j].getPiece().getPieceColor().equals(piece.getPieceColor()) == true) {
                    //break out of for loops
                    i = (-1);
                } else {
                    if (tiles[i][j].getPiece().getPieceType().equals(PieceType.QUEEN)) {
                        counter = 1;
                    }
                    if (tiles[i][j].getPiece().getPieceType().equals(PieceType.ROOK)) {
                        counter = 1;
                    }
                    if (tiles[i][j].getPiece().getPieceType().equals(PieceType.KING)) {
                        counter = 1;
                    }
                }
            } else {
                //do nothing
            }
        }
        System.out.println("horNear " + counter);
        return counter;
    }

    private int checkHorFar(Piece piece) {
        int i = 0;
        int j = 0;
        int counter = 0;
        boolean flag = false;
        int x = piece.getLocation().getRow();
        int y = piece.getLocation().getColumn() - 1;
        x = (x > 7) ? 7 : x;
        y = (y < 0) ? 0 : y;
        //check horizontal to the right
        for (i = x; i < 8; i++) {
            j = y;
            if (tiles[i][j].getPiece() != null) {
                if (tiles[i][j].getPiece().getPieceColor().equals(piece.getPieceColor()) == true) {
                    //break out of for loops
                    i = 9;
                } else {
                    if (!tiles[i][j].getPiece().getPieceType().equals(PieceType.QUEEN) && !tiles[i][j].getPiece().getPieceType().equals(PieceType.ROOK)) {
                        //break out of for loops
                        //flag = true;
                        break;
                    }
                    if (tiles[i][j].getPiece().getPieceType().equals(PieceType.QUEEN) && !flag) {
                        counter = 1;
                    }
                    if (tiles[i][j].getPiece().getPieceType().equals(PieceType.ROOK) && !flag) {
                        counter = 1;
                    }
                }
            } else {
                //do nothing
            }
        }
        x = piece.getLocation().getRow() - 2;
        x = (x < 0) ? 0 : x;
        //check horizontal left
        for (i = x; i >= 0; i--) {
            j = y;
            if (tiles[i][j].getPiece() != null) {
                if (tiles[i][j].getPiece().getPieceColor().equals(piece.getPieceColor()) == true) {
                    //break out of for loops
                    i = (-1);
                } else {
                    if (!tiles[i][j].getPiece().getPieceType().equals(PieceType.ROOK) && !tiles[i][j].getPiece().getPieceType().equals(PieceType.QUEEN)) {
                        //break out of for loops
                        //flag = true;
                        break;
                    }
                    if (tiles[i][j].getPiece().getPieceType().equals(PieceType.QUEEN) && !flag) {
                        counter = 1;
                    }
                    if (tiles[i][j].getPiece().getPieceType().equals(PieceType.ROOK) && !flag) {
                        counter = 1;
                    }
                }
            } else {
                //do nothing
            }
        }
        System.out.println("horFar " + counter);
        return counter;
    }

    private int checkVertNear(Piece piece) {
        int i = 0;
        int j = 0;
        int counter = 0;
        int nearBoundsUp = piece.getLocation().getColumn() + 1;
        int nearBoundsDown = piece.getLocation().getColumn() - 2;
        nearBoundsUp = (nearBoundsUp > 7) ? 7 : nearBoundsUp;
        nearBoundsDown = (nearBoundsDown < 0) ? 0 : nearBoundsDown;
        int x = piece.getLocation().getRow() - 1;
        int y = piece.getLocation().getColumn();
        x = (x > 7) ? 7 : x;
        x = (x < 0) ? 0 : x;
        y = (y < 0) ? 0 : y;
        //check vertical up
        for (j = y; j < nearBoundsUp; j++) {
            i = x;
            if (tiles[i][j].getPiece() != null) {
                if (tiles[i][j].getPiece().getPieceColor().equals(piece.getPieceColor()) == true) {
                    //break out of for loops
                    j = 9;
                } else {
                    if (tiles[i][j].getPiece().getPieceType().equals(PieceType.QUEEN)) {
                        counter = 1;
                    }
                    if (tiles[i][j].getPiece().getPieceType().equals(PieceType.ROOK)) {
                        counter = 1;
                    }
                    if (tiles[i][j].getPiece().getPieceType().equals(PieceType.KING)) {
                        counter = 1;
                    }
                }
            } else {
                //do nothing
            }
        }
        y = piece.getLocation().getColumn() - 2;
        y = (y < 0) ? 0 : y;
        //check vertical left
        for (j = y; j >= nearBoundsDown; j--) {
            i = x;
            if (tiles[i][j].getPiece() != null) {
                if (tiles[i][j].getPiece().getPieceColor().equals(piece.getPieceColor()) == true) {
                    //break out of for loops
                    j = (-1);
                } else {
                    if (tiles[i][j].getPiece().getPieceType().equals(PieceType.QUEEN)) {
                        counter = 1;
                    }
                    if (tiles[i][j].getPiece().getPieceType().equals(PieceType.ROOK)) {
                        counter = 1;
                    }
                    if (tiles[i][j].getPiece().getPieceType().equals(PieceType.KING)) {
                        counter = 1;
                    }
                }
            } else {
                //do nothing
            }
        }
        System.out.println("vertNear " + counter);
        return counter;
    }

    private int checkVertFar(Piece piece) {
        int i = 0;
        int j = 0;
        int counter = 0;
        boolean flag = false;
        int x = piece.getLocation().getRow() - 1;
        int y = piece.getLocation().getColumn();
        x = (x > 7) ? 7 : x;
        y = (y < 0) ? 0 : y;
        //check vertical above
        for (j = y; j < 8; j++) {
            i = x;
            if (tiles[i][j].getPiece() != null) {
                if (tiles[i][j].getPiece().getPieceColor().equals(piece.getPieceColor()) == true) {
                    //break out of for loops
                    j = 9;
                } else {
                    if (!tiles[i][j].getPiece().getPieceType().equals(PieceType.QUEEN) && !tiles[i][j].getPiece().getPieceType().equals(PieceType.ROOK)) {
                        //break out of for loops
                        //flag = true;
                        break;
                    }
                    if (tiles[i][j].getPiece().getPieceType().equals(PieceType.QUEEN) && !flag) {
                        counter = 1;
                    }
                    if (tiles[i][j].getPiece().getPieceType().equals(PieceType.ROOK) && !flag) {
                        counter = 1;
                    }
                }
            } else {
                //do nothing
            }
        }
        y = piece.getLocation().getColumn() - 2;
        y = (y < 0) ? 0 : y;
        //check vertical down
        for (j = y; j >= 0; j--) {
            i = x;
            if (tiles[i][j].getPiece() != null) {
                if (tiles[i][j].getPiece().getPieceColor().equals(piece.getPieceColor()) == true) {
                    //break out of for loops
                    j = (-1);
                } else {
                    if (!tiles[i][j].getPiece().getPieceType().equals(PieceType.ROOK) && !tiles[i][j].getPiece().getPieceType().equals(PieceType.QUEEN)) {
                        //break out of for loops
                        //flag = true;
                        break;
                    }
                    if (tiles[i][j].getPiece().getPieceType().equals(PieceType.QUEEN) && !flag) {
                        counter = 1;
                    }
                    if (tiles[i][j].getPiece().getPieceType().equals(PieceType.ROOK) && !flag) {
                        counter = 1;
                    }
                }
            } else {
                //do nothing
            }
        }
        System.out.println("vertFar " + counter);
        return counter;
    }

    //check for White Knights
    private int checkForWhiteKnights(Piece piece) {
        int i = 0;
        int j = 0;
        int counter = 0;
        int x = piece.getLocation().getRow() - 1;
        int y = piece.getLocation().getColumn() - 1;
        int krx1 = x + 1;
        int krx2 = x + 2;
        int klx1 = x - 1;
        int klx2 = x - 2;
        int kuy1 = y + 1;
        int kuy2 = y + 2;
        int kdy1 = y - 1;
        int kdy2 = y - 2;
        if (krx1 < 8 && kuy2 < 8) {
            if(tiles[krx1][kuy2].getPiece() != null) {
                if (tiles[krx1][kuy2].getPiece().getPieceType().equals(PieceType.KNIGHT) && tiles[krx1][kuy2].getPiece().getPieceColor().equals(Colors.WHITE)) {
                    counter = 1;
                }
            }
        }
        if (krx1 < 8 && kdy2 >= 0){
        if (tiles[krx1][kdy2].getPiece() != null) {
                if (tiles[krx1][kdy2].getPiece().getPieceType().equals(PieceType.KNIGHT) && tiles[krx1][kdy2].getPiece().getPieceColor().equals(Colors.WHITE)) {
                    counter = 1;
                }
        }
        }
        if (tiles[klx1][kuy2].getPiece() != null && klx1 >= 0 && kdy2 >= 0) {
            if (tiles[klx1][kuy2].getPiece().getPieceType().equals(PieceType.KNIGHT) && tiles[klx1][kuy2].getPiece().getPieceColor().equals(Colors.WHITE)) {
                counter = 1;
            }
        }
        if (klx1 >= 0 && kdy2 >= 0) {
            if(tiles[klx1][kdy2].getPiece() != null) {
                if (tiles[klx1][kdy2].getPiece().getPieceType().equals(PieceType.KNIGHT) && tiles[klx1][kdy2].getPiece().getPieceColor().equals(Colors.WHITE)) {
                    counter = 1;
                }
            }
        }
        if (krx2 < 8 && kuy1 < 8) {
            if(tiles[krx2][kuy1].getPiece() != null) {
                if (tiles[krx2][kuy1].getPiece().getPieceType().equals(PieceType.KNIGHT) && tiles[krx2][kuy1].getPiece().getPieceColor().equals(Colors.WHITE)) {
                    counter = 1;
                }
            }
        }
        if(krx2 < 8 && kdy1 >= 0)
        {
            if (tiles[krx2][kdy1].getPiece() != null) {
                if (tiles[krx2][kdy1].getPiece().getPieceType().equals(PieceType.KNIGHT) && tiles[krx2][kdy1].getPiece().getPieceColor().equals(Colors.WHITE)) {
                    counter = 1;
                }
            }
        }
        if(klx2 >= 0 && kuy1 < 8) {
            if (tiles[klx2][kuy1].getPiece() != null) {
                if (tiles[klx2][kuy1].getPiece().getPieceType().equals(PieceType.KNIGHT) && tiles[klx2][kuy1].getPiece().getPieceColor().equals(Colors.WHITE)) {
                    counter = 1;
                }
            }
        }
        if (klx2 >= 0 && kdy1 < 8) {
            if(tiles[klx2][kdy1].getPiece() != null) {
                if (tiles[klx2][kdy1].getPiece().getPieceType().equals(PieceType.KNIGHT) && tiles[klx2][kdy1].getPiece().getPieceColor().equals(Colors.WHITE)) {
                    counter = 1;
                }
            }
        }
        System.out.println("WKnightThreatens " + counter);
        return counter;
    }

    private int checkForBlackKnights(Piece piece) {
        int i = 0;
        int j = 0;
        int counter = 0;
        int x = piece.getLocation().getRow() - 1;
        int y = piece.getLocation().getColumn() - 1;
        int krx1 = x + 1;
        int krx2 = x + 2;
        int klx1 = x - 1;
        int klx2 = x - 2;
        int kuy1 = y + 1;
        int kuy2 = y + 2;
        int kdy1 = y - 1;
        int kdy2 = y - 2;
        if (krx1 < 8 && kuy2 < 8) {
            if (tiles[krx1][kuy2].getPiece() != null) {
                if (tiles[krx1][kuy2].getPiece().getPieceType().equals(PieceType.KNIGHT) && tiles[krx1][kuy2].getPiece().getPieceColor().equals(Colors.BLACK)) {
                    counter = 1;
                }
            }
        }
        if (krx1 < 8 && kdy2 >= 0) {
            if (tiles[krx1][kdy2].getPiece() != null) {
                if (tiles[krx1][kdy2].getPiece().getPieceType().equals(PieceType.KNIGHT) && tiles[krx1][kdy2].getPiece().getPieceColor().equals(Colors.BLACK)) {
                    counter = 1;
                }
            }
        }
        if (klx1 >= 0 && kuy2 < 8) {
            if (tiles[klx1][kuy2].getPiece() != null) {
                if (tiles[klx1][kuy2].getPiece().getPieceType().equals(PieceType.KNIGHT) && tiles[klx1][kuy2].getPiece().getPieceColor().equals(Colors.BLACK)) {
                    counter = 1;
                }
            }
        }
        if (klx1 >= 0 && kdy2 >= 0) {
            if (tiles[klx1][kdy2].getPiece() != null) {
                if (tiles[klx1][kdy2].getPiece().getPieceType().equals(PieceType.KNIGHT) && tiles[klx1][kdy2].getPiece().getPieceColor().equals(Colors.BLACK)) {
                    counter = 1;
                }
            }
        }
        if (krx2 < 8 && kuy1 < 8) {
            if (tiles[krx2][kuy1].getPiece() != null) {
                if (tiles[krx2][kuy1].getPiece().getPieceType().equals(PieceType.KNIGHT) && tiles[krx2][kuy1].getPiece().getPieceColor().equals(Colors.BLACK)) {
                    counter = 1;
                }
            }
        }
        if (krx2 < 8 && kdy1 >= 0) {
            if (tiles[krx2][kdy1].getPiece() != null) {
                if (tiles[krx2][kdy1].getPiece().getPieceType().equals(PieceType.KNIGHT) && tiles[krx2][kdy1].getPiece().getPieceColor().equals(Colors.BLACK)) {
                    counter = 1;
                }
            }
        }
        if (klx2 >= 0 && kuy1 < 8) {
            if (tiles[klx2][kuy1].getPiece() != null) {
                if (tiles[klx2][kuy1].getPiece().getPieceType().equals(PieceType.KNIGHT) && tiles[klx2][kuy1].getPiece().getPieceColor().equals(Colors.BLACK)) {
                    counter = 1;
                }
            }
        }
        if (klx2 >= 0 && kdy1 >= 0) {
            if(tiles[klx2][kdy1].getPiece() != null) {
                if (tiles[klx2][kdy1].getPiece().getPieceType().equals(PieceType.KNIGHT) && tiles[klx2][kdy1].getPiece().getPieceColor().equals(Colors.BLACK)) {
                    counter = 1;
                }
            }
        }
        System.out.println("BKnightThreatens " + counter);
        return counter;
    }
    /*
    *
    * FINISHED CHECKING CODE
    *
    */

}
