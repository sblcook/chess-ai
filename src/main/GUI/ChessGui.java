package GUI;



import Board.Board;
import Board.BoardFactory;
import Pieces.Piece;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.swing.border.*;
import java.io.File;
import javax.imageio.ImageIO;


public class ChessGui {

    private final JPanel gui = new JPanel(new BorderLayout(3, 3));
    private JButton[][] chessBoardSquares = new JButton[8][8];
    private Image[][] chessPieceImages = new Image[2][6];
    private JPanel chessBoard;
    private JPanel rightPanel;
    private JTextArea history;
    private final JLabel message = new JLabel(
            "chess game ready");
    private static final String COLS = "ABCDEFGH";
    public static final int QUEEN = 0, KING = 1,
            ROOK = 2, KNIGHT = 3, BISHOP = 4, PAWN = 5;
    public static final int[] STARTING_ROW = {
            ROOK, KNIGHT, BISHOP, KING, QUEEN, BISHOP, KNIGHT, ROOK
    };
    private boolean firstPick = true;
    int col = -1;
    int row = -1;
    private int pieceRow;
    private int pieceCol;
    public static final int BLACK = 0, WHITE = 1;
    BoardFactory bf = new BoardFactory();
    Board sb = bf.getBoard("StandardBoard");

    ChessGui() {
        initializeGui();
    }

    public final void initializeGui() {
        // create the images for the chess pieces
        createImages();

        // set up the main GUI
        gui.setBorder(new EmptyBorder(5, 5, 5, 5));
        JToolBar tools = new JToolBar();
        setupSidePanel();
        gui.add(rightPanel);





        tools.setFloatable(false);
        gui.add(tools, BorderLayout.PAGE_START);
        Action newGameAction = new AbstractAction("New") {

            @Override
            public void actionPerformed(ActionEvent e) {
                setupNewGame();
            }
        };
        Action printBoard = new AbstractAction("print") {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(int i = 0;i<8;i++){
                    for(int j =0;j<8;j++){
                        if(sb.getTiles()[i][j].getPiece() != null)
                            System.out.print(sb.getTiles()[i][j].getPiece().getPieceType().toString()+", ");
                        else
                            System.out.print("blank"+",");
                    }
                    System.out.println();
                }
            }
        };

        tools.add(newGameAction);
        tools.add(printBoard);
        tools.add(new JButton("Restore")); // TODO - add functionality!
        tools.addSeparator();
        tools.add(new JButton("Resign")); // TODO - add functionality!
        tools.addSeparator();
        tools.add(message);

        gui.add(new JLabel("?"), BorderLayout.LINE_START);

        chessBoard = new JPanel(new GridLayout(0, 9)) {

            /**
             * Override the preferred size to return the largest it can, in
             * a square shape.  Must (must, must) be added to a GridBagLayout
             * as the only component (it uses the parent as a guide to size)
             * with no GridBagConstaint (so it is centered).
             */
            @Override
            public final Dimension getPreferredSize() {
                Dimension d = super.getPreferredSize();
                Dimension prefSize = null;
                Component c = getParent();
                if (c == null) {
                    prefSize = new Dimension(
                            (int)d.getWidth(),(int)d.getHeight());
                } else if (c!=null &&
                        c.getWidth()>d.getWidth() &&
                        c.getHeight()>d.getHeight()) {
                    prefSize = c.getSize();
                } else {
                    prefSize = d;
                }
                int w = (int) prefSize.getWidth();
                int h = (int) prefSize.getHeight();
                // the smaller of the two sizes
                int s = (w>h ? h : w);
                return new Dimension(s,s);
            }
        };
        chessBoard.setBorder(new CompoundBorder(
                new EmptyBorder(8,8,8,8),
                new LineBorder(Color.BLACK)
        ));
        // Set the BG to be ochre
        Color ochre = new Color(204,119,34);
        chessBoard.setBackground(ochre);
        JPanel boardConstrain = new JPanel(new GridBagLayout());
        boardConstrain.setBackground(ochre);
        boardConstrain.add(chessBoard);
        gui.add(boardConstrain);




        // create the chess board squares
        Insets buttonMargin = new Insets(0, 0, 0, 0);


        for (int ii = 0; ii < chessBoardSquares.length; ii++) {
            for (int jj = 0; jj < chessBoardSquares[ii].length; jj++) {
                JButton b = new JButton();
                int finalJj = jj;
                int finalIi = ii;
                b.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {


                            row = finalIi;
                            col = finalJj;
                            System.out.println("here");


                        if(firstPick){
                            if(sb.getTiles()[row][col].getPiece()!= null) {
                                chessBoardSquares[col][row].setBorder(new LineBorder(Color.red, 2));
                                pieceRow = row;
                                pieceCol = col;
                                firstPick = false;
                                System.out.println(row+","+col);

                            }else{
                                System.out.println(row+","+col);
                                firstPick=true;

                            }
                        }else{
                            chessBoardSquares[col][row].setIcon(imageOf(sb.getTiles()[pieceRow][pieceCol].getPiece()));
                            chessBoardSquares[pieceCol][pieceRow].setIcon(imageOf(null));
                            chessBoardSquares[pieceCol][pieceRow].setBorder(new LineBorder(Color.black, 0));



                            sb.getTiles()[row][col].setPiece(sb.getTiles()[pieceRow][pieceCol].getPiece());
                            sb.getTiles()[pieceRow][pieceCol].setPiece(null);
                            System.out.println("from "+pieceRow+","+pieceCol);
                            System.out.println("to "+row+","+col);
                            firstPick=true;


                        }


                    }
                });
                b.setMargin(buttonMargin);
                // our chess pieces are 64x64 px in size, so we'll
                // 'fill this in' using a transparent icon..
                ImageIcon icon = new ImageIcon(
                        new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
                b.setIcon(icon);
                if ((ii % 2 == 1 && jj % 2 == 1)
                        //) {
                        || (ii % 2 == 0 && jj % 2 == 0)) {
                    b.setBackground(Color.WHITE);
                } else {
                    b.setBackground(Color.BLACK);
                }
                chessBoardSquares[jj][ii] = b;
            }
        }

        /*
         * fill the chess board
         */
        chessBoard.add(new JLabel(""));
        // fill the top row
        for (int ii = 0; ii < 8; ii++) {
            chessBoard.add(
                    new JLabel(COLS.substring(ii, ii + 1),
                            SwingConstants.CENTER));
        }
        // fill the black non-pawn piece row
        for (int ii = 0; ii < 8; ii++) {
            for (int jj = 0; jj < 8; jj++) {
                switch (jj) {
                    case 0:
                        chessBoard.add(new JLabel("" + (9-(ii + 1)),
                                SwingConstants.CENTER));
                    default:
                        chessBoard.add(chessBoardSquares[jj][ii]);
                }
            }
        }
    }

    public final JPanel getGui() {
        return gui;
    }

    private final void createImages() {
        try {
            File file = new File("src/images/pieces.png");
            BufferedImage bi = ImageIO.read(new File(file.toURI()));
            for (int ii = 0; ii < 2; ii++) {
                for (int jj = 0; jj < 6; jj++) {
                    chessPieceImages[ii][jj] = bi.getSubimage(
                            jj * 64, ii * 64, 64, 64);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Initializes the icons of the initial chess board piece places
     */
    private final void setupNewGame() {
        message.setText("Make your move!");




        // set up all pieces
        for(int row =0;row<8;row++){
            for(int col =0;col<8;col++){



                    chessBoardSquares[col][row].setIcon(imageOf(sb.getTiles()[row][col].getPiece()));
            }
        }


    }
    private void setupSidePanel(){
        this.history = new JTextArea("History:");
        this.history.setEditable(false);
        this.history.setPreferredSize(new Dimension(200, 750));
        this.history.setBackground(new Color(247, 249, 249));

        rightPanel = new JPanel(new GridLayout(2, 1));
        rightPanel.setPreferredSize(new Dimension(200, 750));
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBackground(new Color(247, 249, 249));
        rightPanel.setBorder(new CompoundBorder(
                new EmptyBorder(8,8,8,8),
                new LineBorder(Color.BLACK)
        ));
        JLabel message2 = new JLabel(
                "SIDE PANEL");
        rightPanel.add(history);
        rightPanel.add(message2);


    }

    private ImageIcon imageOf(Piece piece) {
        if (piece == null)
            return new ImageIcon(new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
        else {
            switch (piece.getPieceType()) {
                case ROOK:
                    if (piece.getPieceColor().toString().equalsIgnoreCase("WHITE"))
                        return new ImageIcon(chessPieceImages[1][2]);
                    else
                        return new ImageIcon(chessPieceImages[0][2]);

                case KING:
                    if (piece.getPieceColor().toString().equalsIgnoreCase("WHITE"))
                        return new ImageIcon(chessPieceImages[1][0]);
                    else
                        return new ImageIcon(chessPieceImages[0][0]);
                case QUEEN:
                    if (piece.getPieceColor().toString().equalsIgnoreCase("WHITE"))
                        return new ImageIcon(chessPieceImages[1][1]);
                    else
                        return new ImageIcon(chessPieceImages[0][1]);

                case BISHOP:
                    if (piece.getPieceColor().toString().equalsIgnoreCase("WHITE"))
                        return new ImageIcon(chessPieceImages[1][4]);
                    else
                        return new ImageIcon(chessPieceImages[0][4]);
                case PAWN:
                    if (piece.getPieceColor().toString().equalsIgnoreCase("WHITE"))
                        return new ImageIcon(chessPieceImages[1][5]);
                    else
                        return new ImageIcon(chessPieceImages[0][5]);
                case KNIGHT:
                    if (piece.getPieceColor().toString().equalsIgnoreCase("WHITE"))
                        return new ImageIcon(chessPieceImages[1][3]);
                    else
                        return new ImageIcon(chessPieceImages[0][3]);

                default:
                    return null;
            }
        }


    }







}