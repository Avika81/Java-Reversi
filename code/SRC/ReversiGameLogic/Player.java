
package ReversiBase;


import javafx.scene.paint.Color;

public interface Player {
  /**
   * this method appears when the player has no move
   * @param display a given display of the game (endgame print)
   */
  void noMove(Display display);


    /**
     * check if the player is the starter player
     * @return true if he starts, false otherwise
     */
    boolean isStarter();
    /**
     * @return the color of the player
     */
    Color getColor();

    /**
     * This method asks the user to pick he's selected move.
     * @param positions possible moves.
     * @param moves number of positions.
     * @param opponentStat color of the opponent.
     * @param display display.
     * @return user's decided move.
     */
    Pair getMove(Pair positions[], int moves, Color opponentStat, Display display);

}
