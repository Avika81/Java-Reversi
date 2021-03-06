
package ReversiBase;

import javafx.scene.paint.Color;

public class ConsoleDisplay implements Display {
    /**
     * this method prints the board
     * @param board a given board
     */
    @Override
    public void printBoard(Board board) {
        Color startingColor = board.getStartingColor();
        Color notStartingColor = board.getNotStartingColor();
        int size = board.getSize();
        GamePiece[][] gamePieces;
        gamePieces = board.getGamePieces();

        int i, j;
        char symbol = ' '; // an empty space
        System.out.print(" |") ;
        for (i = 0; i < size; i++) {
            System.out.print(" " + (i+1) + " |");
        }
        System.out.print(" \n"); //prints the new line
        System.out.print("--");
        for (i = 0; i < size; i++) {
            System.out.print("----");
        }
        System.out.print(" \n");
        for (i = 0; i < size; i++) {
            System.out.print((i+1) + "|");
            for (j = 0; j < size; j++) {
                if (gamePieces[i][j].isEmpty()) {
                    symbol = ' '; // an empty space
                } else {
                    if (gamePieces[i][j].getColor().toString().equals(notStartingColor.toString())) {
                        symbol = 'O'; // player O
                    }
                    if (gamePieces[i][j].getColor().toString().equals(startingColor.toString())) {
                        symbol = 'X'; // player X
                    }
                }
                System.out.print(" " + symbol + " |");
            }
            System.out.print(" \n");
            System.out.print("--");
            for (j = 0; j < size; j++) {
                System.out.print("----");
            }
            System.out.print(" \n");
        }
    }
    /**
     * this method prints possible Moves for player x position.
     * @param positions given positions
     * @param moves number of moves
     */
    @Override
    public void printPossibleMoves(Pair[] positions, int moves) {
        System.out.print("Your possible moves: "); //the start of the line
        for (int i = 0; i < moves; i++) {
            if (i != 0) {
                System.out.print(","); // after each print ,
            }
            printPair(positions[i]);
        }
        System.out.print("\n\n"); // two new lines.
    }

    /**
     * this method prints a pair. (standart function)
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
        if (p.isStarter()) {
            System.out.println("Player1: It's your move.");
        } else {
            System.out.println("Player2: It's your move.");
        }
    }

    /**
     * this method prints that a player has no possible moves
     * @param p a given player
     */
    @Override
    public void noPossiblePlayerMove(Player p) {
        System.out.print("No possible moves for ");
        if (!p.isStarter()) {
            System.out.println("O");
        } else {
            System.out.println("X");
        }
        this.printString("Play passes back to other player");
    }
}
