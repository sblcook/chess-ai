package GUI;

import Board.StandardBoard;
import Board.Tile;
import Enums.Colors;

import javax.swing.*;
import java.awt.*;


public class GameDisplay extends JPanel {

    StandardBoard board;

    int squareSize;

    public GameDisplay(StandardBoard gameBoard, int squareSize){
        board = gameBoard;
        this.squareSize = squareSize;
    }

    @Override
    public void paintComponent(Graphics graphic){
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                Tile tile = board.getTiles()[i][j];
                if(tile.tileColor.equals(Colors.BLACK)){
                    graphic.setColor(new Color(58,95,205));
                    graphic.fillRect((squareSize*i), (7-j)*squareSize, squareSize, squareSize);
//                    if(tile.isOccupied)
//                        tile.occupyingPiece.drawPiece(graphic, squareSize, i, j);
                }
                else{
                    graphic.setColor(new Color(230, 230, 250));
                    graphic.fillRect((squareSize*i), (7-j)*squareSize, squareSize, squareSize);
//                    if(tile.isOccupied)
//                        tile.occupyingPiece.drawPiece(graphic, squareSize, i, j);
                }
            }
        }
    }

    //this should display a GUI but to do so, the tile class has to have the addition of a boolean and a color varirable
    //the factory also has to set the tile colors programmatically






}

