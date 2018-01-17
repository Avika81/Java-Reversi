
package ReversiBase;


import javafx.scene.paint.Color;

public class GamePiece {
    private Color MyColor;
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
     * this method returns MyColor of the cell
     * @return MyColor og the cell
     */
    public Color getColor() {
        if (this.isEmpty) {
            return Color.WHEAT;
        }
        return MyColor;
    }

    /**
     * this method sets the MyColor og the cell
     * @param MyColor a given MyColor
     */
    public void setColor(Color MyColor) {
        this.MyColor = MyColor;
        this.isEmpty = false;
    }
}
