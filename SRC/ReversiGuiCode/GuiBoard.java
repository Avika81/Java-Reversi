/*
 * Tomer Grady 205660863
 * Raz Shenkman 311130777
 */
package ReversiGUI;

import ReversiBase.Board;
import ReversiBase.GamePiece;
import ReversiBase.Pair;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.io.IOException;

import static java.lang.Math.min;

/**
 * This class holds a board and is in charge of printing it.
 */
public class GuiBoard extends GridPane {
    private Board board;
    private double HightCell;
    private double widthCell;

    /**
     * Constructor from a board.
     *
     * @param board
     */
    public GuiBoard(Board board) {
        this.board = board;
    }

    /**
     * Sets a new board for the guiBoard.
     *
     * @param board
     */
    public void setBoard(Board board) {
        this.board = board;
    }

    /**
     * Draws the board.
     */
    public void draw() {
        this.getChildren().clear();
        double height = this.getPrefHeight();
        double width = this.getPrefWidth();
        double cellHeight = height / board.getSize();
        double widthCell = width / board.getSize();
        double radius = (Math.min(cellHeight, widthCell) / 2);
        this.HightCell = (height / board.getSize()) + 1;
        this.widthCell = (width / board.getSize()) + 1;
        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                Rectangle rec = new Rectangle(widthCell, cellHeight, Color.WHEAT);
                rec.setStroke(Color.BLACK);
                rec.setStrokeWidth(1);
                StackPane pane = new StackPane();
                pane.getChildren().addAll(rec);
                //this.add(pane, j, i);
                if (!board.getGamePieces()[i][j].isEmpty()) {
                    pane.getChildren().add(new Circle(((widthCell + 1) / 2) * i,
                            ((cellHeight + 1) / 2) * j, radius, board.getGamePieces()[i][j].getColor()));
                    this.add(pane, j, i);
                } else {
                    this.add(pane, j, i);
                }
            }
        }
    }

    /**
     * Returns the cell width.
     *
     * @return cell width.
     */
    public double getwidthCell() {
        return this.widthCell;
    }

    /**
     * Returns the cell height.
     *
     * @return cell height.
     */
    public double getHightCell() {
        return this.HightCell;
    }

}
