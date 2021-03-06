
package ReversiBase;


import javafx.scene.paint.Color;

public class GamePiece {
    private Color mColor;
    private boolean isEmpty;

    /**
     * this method checks if the cell is empty
     * @return true if empty false otherwise
     */
    public boolean isEmpty() {
        return isEmpty;
    }

    /**
     * This method returns if the game piece is empty.
     */
    GamePiece() {
        this.isEmpty = true;
    }

    /**
     * this method returns the color of the cell
     * @return mColor og the cell
     */
    public Color getColor() {
        if (this.isEmpty) {
            return Color.WHEAT; //default value
        }
        return mColor;
    }

    /**
     * this method sets the color og the cell
     * @param color a given color
     */
    public void setColor(Color color) {
        this.mColor = color;
        this.isEmpty = false;
    }
}
