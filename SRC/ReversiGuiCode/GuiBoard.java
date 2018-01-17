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
    private double cellHight;
    private double cellWidth;

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
        double cellWidth = width / board.getSize();
        double radius = (Math.min(cellHeight, cellWidth) / 2);
        this.cellHight = (height / board.getSize()) + 1;
        this.cellWidth = (width / board.getSize()) + 1;
        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                Rectangle rec = new Rectangle(cellWidth, cellHeight, Color.WHEAT);
                rec.setStroke(Color.BLACK);
                rec.setStrokeWidth(1);
                StackPane pane = new StackPane();
                pane.getChildren().addAll(rec);
                //this.add(pane, j, i);
                if (!board.getGamePieces()[i][j].isEmpty()) {
                    pane.getChildren().add(new Circle(((cellWidth + 1) / 2) * i,
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
    public double getCellwidth() {
        return this.cellWidth;
    }

    /**
     * Returns the cell height.
     *
     * @return cell height.
     */
    public double getCellHight() {
        return this.cellHight;
    }

}