
package ReversiBase;
import javafx.scene.paint.Color;

/**
 * This class features a game board.
 */
public class Board {
    private GamePiece[][] PiecesMatrix;
    private int SizeOfBoard;
    private Color defaultColorPlayer1;
    private Color defaultColorPlayer2;

    /**
     * This constructor creates a board from an inputted size.
     *
     * @param size-            wanted size for the board.
     * @param defaultColorPlayer1    given starting color
     * @param defaultColorPlayer2 given not starting color
     */
    public Board(int size, Color defaultColorPlayer1, Color defaultColorPlayer2) {
        this.SizeOfBoard = size;
        gamePieces = new GamePiece[size][size];
        this.defaultColorPlayer1 = defaultColorPlayer1;
        this.defaultColorPlayer2 = defaultColorPlayer2;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.PiecesMatrix[i][j] = new GamePiece();
            }
        }
        this.PiecesMatrix[this.SizeOfBoard / 2 - 1][this.SizeOfBoard / 2 - 1].setColor(defaultColorPlayer2);
        this.PiecesMatrix[this.SizeOfBoard / 2][this.SizeOfBoard / 2].setColor(defaultColorPlayer2);
        this.PiecesMatrix[this.SizeOfBoard / 2 - 1][this.SizeOfBoard / 2].setColor(defaultColorPlayer1);
        this.PiecesMatrix[this.SizeOfBoard / 2][this.SizeOfBoard / 2 - 1].setColor(defaultColorPlayer1);
    }

    /**
     * This method returns status of desired cell.
     *
     * @param p inputted pair for getting the cell.
     * @return cell's status.
     */
    public GamePiece getCellStatus(Pair p) {
        if (p.getRow() >= 0 && p.getRow() < this.SizeOfBoard && p.getCol() >= 0 && p.getCol() < this.SizeOfBoard) {
            return this.PiecesMatrix[p.getRow()][p.getCol()];
        }
        GamePiece emptyGamePiece = new GamePiece();
        return emptyGamePiece;
    }

    /**
     * This method changes status of desired cell.
     *
     * @param p inputted pair for getting the cell.
     * @param c desired input Color.
     */
    public void changeStatus(Pair p, Color c) {
        if (p.getRow() >= 0 && p.getRow() < this.SizeOfBoard && p.getCol() >= 0 && p.getCol() < this.SizeOfBoard) {
            this.PiecesMatrix[p.getRow()][p.getCol()].setColor(c);
        }
    }

    /**
     * this method gets the matrix of the board
     *
     * @return the matrix of the board
     */
    public GamePiece[][] getGamePieces() {
        return this.PiecesMatrix;
    }

    /**
     * This method checks if all the board cells have values in them.
     *
     * @return true/false.
     */
    public boolean isBoardFull() {
        for (int i = 0; i < this.SizeOfBoard; i++) {
            for (int j = 0; j < this.SizeOfBoard; j++) {
                if (this.PiecesMatrix[i][j].isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Return the size of the board.
     *
     * @return
     */
    public int getSize() {
        return this.SizeOfBoard;
    }

    /**
     * Return the color of the player1.
     *
     * @return color of p[layer 1
     */
    public Color getStartingColor() {
        return this.defaultColorPlayer1;
    }

    /**
     * Return the color of the player2.
     *
     * @return color of p[layer 2
     */
    public Color getNotStartingColor() {
        return this.defaultColorPlayer2;
    }

}
