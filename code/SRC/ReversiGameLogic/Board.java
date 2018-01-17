defaultColorForFirst
package ReversiBase;
import javafx.scene.paint.Color;

/**
 * This class features a game board.
 */
public class Board {
    private GamePiece[][] gamePieceMatrix;
    private int size;
    private Color defaultColorForFirst;
    private Color defaultColorForSecond;

    /**
     * This constructor creates a board from an inputted size.
     *
     * @param size-            wanted size for the board.
     * @param defaultColorForFirst    given starting color
     * @param defaultColorForSecond given not starting color
     */
    public Board(int size, Color defaultColorForFirst, Color defaultColorForSecond) {
        this.size = size;
        gamePieceMatrix = new GamePiece[size][size];
        this.defaultColorForFirst = defaultColorForFirst;
        this.defaultColorForSecond = defaultColorForSecond;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.gamePieceMatrix[i][j] = new GamePiece();
            }
        }
        this.gamePieceMatrix[this.size / 2 - 1][this.size / 2 - 1].setColor(defaultColorForSecond);
        this.gamePieceMatrix[this.size / 2][this.size / 2].setColor(defaultColorForSecond);
        this.gamePieceMatrix[this.size / 2 - 1][this.size / 2].setColor(defaultColorForFirst);
        this.gamePieceMatrix[this.size / 2][this.size / 2 - 1].setColor(defaultColorForFirst);
    }

    /**
     * This method returns status of desired cell.
     *
     * @param p inputted pair for getting the cell.
     * @return cell's status.
     */
    public GamePiece getCellStatus(Pair p) {
        if (p.getRow() >= 0 && p.getRow() < this.size && p.getCol() >= 0 && p.getCol() < this.size) {
            return this.gamePieceMatrix[p.getRow()][p.getCol()];
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
        if (p.getRow() >= 0 && p.getRow() < this.size && p.getCol() >= 0 && p.getCol() < this.size) {
            this.gamePieceMatrix[p.getRow()][p.getCol()].setColor(c);
        }
    }

    /**
     * this method gets the matrix of the board
     *
     * @return the matrix of the board
     */
    public GamePiece[][] getGamePieces() {
        return this.gamePieceMatrix;
    }

    /**
     * This method checks if all the board cells have values in them.
     *
     * @return true/false.
     */
    public boolean isBoardFull() {
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                if (this.gamePieceMatrix[i][j].isEmpty()) {
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
        return this.size;
    }

    /**
     * Return the color of the player1.
     *
     * @return color of p[layer 1
     */
    public Color getStartingColor() {
        return this.defaultColorForFirst;
    }

    /**
     * Return the color of the player2.
     *
     * @return color of p[layer 2
     */
    public Color getNotStartingColor() {
        return this.defaultColorForSecond;
    }

}
