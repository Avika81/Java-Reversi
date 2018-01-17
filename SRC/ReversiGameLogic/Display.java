
package ReversiBase;
/**
  * abstractclass for Display of the game. contains ConsoleDisplay
  * abstract functinos/
**/
public interface Display {

    /**
     * this method prints the board
     * @param boardToPrint a given board
     */
    void printBoard(Board boardToPrint);

    /**
     * this method prints possible Moves
     * @param positions positions
     * @param moves number of moves
     */
    void printPossibleMoves(Pair[] positions, int moves);

    /**
     * this method prints a pair.
     * @param p a given pair
     */
    void printPair(Pair p);

    /**
     * this method prints a string.
     * @param string a given string
     */
    void printString(String string);

    /**
     * this method prints it's Your Move for a player.
     */
    void itsYourMove(Player p);

    /**
     * this method prints that a player has no possible moves
     * @param p a given player
     */
    void noPossiblePlayerMove(Player p);

}
