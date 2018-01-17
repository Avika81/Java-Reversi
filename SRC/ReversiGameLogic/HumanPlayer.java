
package ReversiBase;

import javafx.scene.paint.Color;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class HumanPlayer implements Player {
    private boolean isFirstPlayer;
    private Color gamePieceColor;

    /**
     * Constructor for human player.
     * @param isFirstPlayer
     * @param gamePieceColor
     */
    HumanPlayer(boolean isFirst, Color gamePieceColor) {
        this.isFirstPlayer = isFirst;
        this.gamePieceColor = gamePieceColor;
    }

    /**
     * This method asks the user to pick he's selected move.
     *
     * @param positions     possible moves.
     * @param moves         number of positions.
     * @param opponentColor color of the opponent.
     * @param display       display.
     * @return user's decided move.
     */
    public Pair getMove(Pair positions[], int moves, Color opponentColor, Display display) {
        int xUser, yUser;
        xUser = -5;
        yUser = -5;

        display.itsYourMove(this);
        display.printPossibleMoves(positions, moves);
        display.printString("Please enter your move row (space) col: ");
        String point = null;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            point = reader.readLine();
            xUser = Character.getNumericValue(point.charAt(0));
            yUser = Character.getNumericValue(point.charAt(2));
        } catch (Exception e) {
        }
        return new Pair(xUser, yUser);
    }

    /**
     * check if the player is the starter player
     *
     * @return true if he starts, false otherwise
     */
    public boolean isStarter() {
        return this.isFirstPlayer;
    }

    /**
     * this method appears when the player has no move
     *
     * @param display a given display of the game
     */
    public void noMove(Display display) {
        String anyKey;
        display.noPossiblePlayerMove(this);
//        getline(cin, anyKey);
//        do
//        {
//            cout << '\n' << "Press a key to continue...";
//        } while (cin.get() != '\n');
        // prints a new line
        display.printString("");
    }

    /**
     * the method returns the color of the player
     *
     * @return the color of the player
     */
    public Color getColor() {
        return this.gamePieceColor;
    }

    /**
     * this method sets the color of the player
     *
     * @param gamePieceColor
     */
    public void setColor(Color gamePieceColor) {
        this.gamePieceColor = gamePieceColor;
    }
}
