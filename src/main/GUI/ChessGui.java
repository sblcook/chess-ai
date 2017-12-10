package GUI;



import Board.Board;
import Board.BoardFactory;
import Pieces.Piece;
import com.sun.glass.ui.Pixels;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;


import javax.swing.*;
import javax.swing.border.*;
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import javax.imageio.ImageIO;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import javax.swing.text.JTextComponent;


public class ChessGui {

    private final JPanel gui = new JPanel(new BorderLayout(3, 3));
    private JButton[][] chessBoardSquares = new JButton[8][8];
    private Image[][] chessPieceImages = new Image[2][6];
    private JPanel chessBoard;
    private JPanel holder;

    ArrayList<String> moves = new ArrayList<>();


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
        initComponents();

        gui.add(holder);









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
                            //put logic here to check for valid move
                            chessBoardSquares[col][row].setIcon(imageOf(sb.getTiles()[pieceRow][pieceCol].getPiece()));
                            chessBoardSquares[pieceCol][pieceRow].setIcon(imageOf(null));
                            chessBoardSquares[pieceCol][pieceRow].setBorder(new LineBorder(Color.black, 0));



                            sb.getTiles()[row][col].setPiece(sb.getTiles()[pieceRow][pieceCol].getPiece());
                            sb.getTiles()[pieceRow][pieceCol].setPiece(null);
                            System.out.println("from "+pieceRow+","+pieceCol);
                            System.out.println("to "+row+","+col);
                            firstPick=true;
                            String moveString = "moved "+sb.getTiles()[row][col].getPiece().getPieceType()
                                    +
                                    "from "+pieceRow+","+pieceCol+" to"+row+","+col;


                            model.addElement(moveString);

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
    //working here to make side panel
    private JLabel capturedWhite;
    private JLabel capturedBlack;
    private JLabel moveHistory;
    private JLabel capturedPieces;
    private JList<String> capturedWhitePieceList;
    private JList<String> capturedBlackPieceList;
    private JPanel sidePanel;
    private JScrollPane historyPane;
    private JScrollPane jScrollPane2;
    private JScrollPane jScrollPane4;
    private JSeparator jSeparator1;
    private JList<String> moveListHistory;
    private DefaultListModel model;

    private void initComponents() {

        sidePanel = new JPanel();
        capturedBlack = new JLabel();
        capturedWhite = new JLabel();
        jScrollPane2 = new JScrollPane();
        capturedWhitePieceList = new JList<>();
        jScrollPane4 = new JScrollPane();
        capturedBlackPieceList = new JList<>();
        jSeparator1 = new JSeparator();
        moveHistory = new JLabel();

        capturedPieces = new JLabel();
        sidePanel.setBackground(new Color(153, 153, 153));

        model = new DefaultListModel();
        moveListHistory = new JList(model);
        model.addElement("game started. ");
        historyPane = new JScrollPane(moveListHistory);







        capturedBlack.setText("Black");

        capturedWhite.setText("White");

        capturedWhitePieceList.setModel(new AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }

        });
        jScrollPane2.setViewportView(capturedWhitePieceList);

        capturedBlackPieceList.setModel(new AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane4.setViewportView(capturedBlackPieceList);

        moveHistory.setText("Piece Move History");

        moveHistory.setHorizontalAlignment(SwingConstants.CENTER);










        GroupLayout sidePanelLayout = new GroupLayout(sidePanel);

        sidePanel.setLayout(sidePanelLayout);

        sidePanelLayout.setHorizontalGroup(
                sidePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(sidePanelLayout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addComponent(jScrollPane2, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
                                .addGap(37, 37, 37)
                                .addComponent(jScrollPane4, GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE)
                                .addGap(18, 18, 18))
                        .addGroup(sidePanelLayout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addComponent(capturedWhite)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(capturedBlack)
                                .addGap(38, 38, 38))
                        .addGroup(GroupLayout.Alignment.TRAILING, sidePanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jSeparator1)
                                .addContainerGap())
                        .addGroup(GroupLayout.Alignment.CENTER, sidePanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(moveHistory)
                                .addContainerGap())
                        .addGroup(sidePanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(historyPane)
                                .addContainerGap())
        );

        sidePanelLayout.setVerticalGroup(
                sidePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(sidePanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(sidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(capturedWhite)
                                        .addComponent(capturedBlack))
                                .addGap(18, 18, 18)
                                .addGroup(sidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(25, 25, 25)
                                .addComponent(moveHistory)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(historyPane, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(42, Short.MAX_VALUE))
        );


        GroupLayout holderLayout = new GroupLayout(holder = new JPanel());
        holder.setLayout(holderLayout);
        holderLayout.setHorizontalGroup(
                holderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(holderLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(chessBoard.getParent(), javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(sidePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );
        holderLayout.setVerticalGroup(
                holderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, holderLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(sidePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
                        .addGroup(holderLayout.createSequentialGroup()
                                .addComponent(chessBoard.getParent(), javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 11, Short.MAX_VALUE))
        );


    }// </editor-fold>

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