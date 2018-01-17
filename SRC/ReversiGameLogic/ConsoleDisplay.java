
package ReversiBase;

import javafx.scene.paint.Color;

public class ConsoleDisplay implements Display {
    /**
     * this method prints the board
     * @param board a given board
     */
    @Override
    public void printBoard(Board board) {
        int size = board.getSize();
        GamePiece[][] gamePieces;
        gamePieces = board.getGamePieces();
        Color startingColor = board.getStartingColor();
        Color notStartingColor = board.getNotStartingColor();
        int i, j;
        char symbol = ' ';
        System.out.print(" |") ;
        for (i = 0; i < size; i++) {
            System.out.print(" " + (i+1) + " |");
        }
        System.out.println(" ");
        System.out.print("--");
        for (i = 0; i < size; i++) {
            System.out.print("----");
        }
        System.out.println(" ");
        for (i = 0; i < size; i++) {
            System.out.print((i+1) + "|");
            for (j = 0; j < size; j++) {
                if (gamePieces[i][j].isEmpty()) {
                    symbol = ' ';
                } else {
                    if (gamePieces[i][j].getColor().toString().equals(notStartingColor.toString())) {
                        symbol = 'O';
                    }
                    if (gamePieces[i][j].getColor().toString().equals(startingColor.toString())) {
                        symbol = 'X';
                    }
                }
                System.out.print(" " + symbol + " |");
            }
            System.out.println(" ");
            System.out.print("--");
            for (j = 0; j < size; j++) {
                System.out.print("----");
            }
            System.out.println(" ");
        }
    }
    /**
     * this method prints possible Moves
     * @param positions given positions
     * @param moves number of moves
     */
    @Override
    public void printPossibleMoves(Pair[] positions, int moves) {
        System.out.print("Your possible moves: ");
        for (int i = 0; i < moves; i++) {
            if (i != 0) {
                System.out.print(",");
            }
            printPair(positions[i]);
        }
        System.out.println("");
        System.out.println("");
    }
    /**
     * this method prints a pair.
     * @param p a given pair
     */
    @Override
    public void printPair(Pair p) {
        System.out.print("(" + (p.getRow() + 1) + "," + (p.getCol() + 1) + ")");

    }
    /**
     * this method prints a stribf.
     * @param string a given string
     */
    @Override
    public void printString(String string) {
        System.out.println(string);
    }
    /**
     * this method prints it's Your Move for a player.
     */
    @Override
    public void itsYourMove(Player p) {
        if (!p.isStarter()) {
            System.out.println("Player2: It's your move.");
        } else {
            System.out.println("Player1: It's your move.");
        }
    }
    /**
     * this method prints that a player has no possible moves
     * @param p a given player
     */
    @Override
    public void noPossiblePlayerMove(Player p) {
        System.out.print("No possible moves for ");
        if (p.isStarter()) {
            System.out.println("X");
        } else {
            System.out.println("O");
        }
        this.printString("Play passes back to other player");
    }
}
