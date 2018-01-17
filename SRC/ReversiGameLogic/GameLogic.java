
package ReversiBase;


import javafx.scene.paint.Color;

public abstract class GameLogic {
    protected Board gameBoard;
    protected Color FirstColor, SecondColor;

    /**
     * This method checks if the game should end.
     *
     * @param noMoreActionsB boolean for the black player possible move (have moves or don't have moves).
     * @param noMoreActionsW boolean for the while player possible move (have moves or don't have moves).
     * @return
     */
    public boolean checkAndAnnounceFinish(boolean noMoreActionsB, boolean noMoreActionsW, Display display) {
        if (this.gameBoard.isBoardFull()) {
            display.printString("Current board:");
            display.printBoard(board);
            display.printString("The board is full");

            if (this.whoWon() == GameWinner.Draw) {
                display.printString("It's a draw");
            }
            else if (this.whoWon() == GameWinner.BlackWon) {
                display.printString(this.FirstColor.toString() + " player wins");
            }
            else {
                display.printString(this.SecondColor.toString() + " player wins");
            }
            return true;
        }
        if (noMoreActionsB && noMoreActionsW) {
            display.printString("No more moves available for both players: ");
            if (this.whoWon() == GameWinner.Draw) {
                display.printString("It's a draw");
            }
            else if (this.whoWon() == GameWinner.BlackWon) {
                display.printString(this.FirstColor.toString() + " player wins");
            }
            else {
                display.printString(this.SecondColor.toString() + " player wins");
            }
            return true;
        }
        return false;
    }

    /**
     * standart getter
     */
    public Board getBoard() {
        return this.gameBoard;
    }

    /**
     * This method returns who won the game, or draw by scanning the board cells.
     *
     * @return who won the game, or draw
     */
    public GameWinner whoWon() {
        int size = this.getBoardSize();
        int firstScore = 0;
        int secondScore = 0;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (this.gameBoard.getCellStatus(new Pair(i, j)).getColor().toString()
                        .equals(SecondColor.toString())) {
                    secondScore++;
                } else if (this.gameBoard.getCellStatus(new Pair(i, j)).getColor().toString()
                        .equals(FirstColor.toString())) {
                    firstScore++;
                }
            }
        }
        if (firstScore > secondScore) {
            return GameWinner.BlackWon;
        } else if (secondScore > firstScore) {
            return GameWinner.WhiteWon;
        } else {
            return GameWinner.Draw;
        }
    }

    /**
     * This method returns the size of the board.
     *
     * @return size of the board
     */
    public int getBoardSize() {
        return this.gameBoard.getSize();
    }

    public GameLogic() {
    }

    /**
     * This constructor creates a basic game logic abstract class.
     *
     * @param board            inputted board.
     * @param FirstColor    inputted FirstColor.
     * @param SecondColor inputted SecondColor.
     */
    public GameLogic(Board board, Color FirstColor, Color SecondColor) {
        this.gameBoard = board;
        this.SecondColor = SecondColor;
        this.FirstColor = FirstColor;
    }

    /**
     * thic method gets first player advantage
     *
     * @return return the first player advantage (can be negeative)
     */
    public int getFirstPlayerAdvantage() {
        int firstScore = 0;
        int secondScore = 0;
        int boardSize = this.getBoardSize();
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (this.gameBoard.getCellStatus(new Pair(i, j)).getColor().toString()
                        .equals(this.SecondColor.toString())) {
                    secondScore++;
                } else if (this.gameBoard.getCellStatus(new Pair(i, j))
                        .getColor().toString().equals(this.FirstColor.toString())) {
                    firstScore++;
                }
            }
        }
        return (firstScore - secondScore); //returns the diffrence.
    }

    /**
     * thic method gets first player score
     *
     * @return return the first player score
     */
    public int getFirstPlayerScore() {
        int firstScore = 0;
        int boardSize = this.getBoardSize();
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (this.gameBoard.getCellStatus(new Pair(i, j)).getColor().toString()
                        .equals(this.FirstColor.toString())) {
                    firstScore++;
                }
            }
        }
        return firstScore;
    }

    /**
     * thic method gets second player score
     *
     * @return return the second player score
     */
    public int getSecondPlayerScore() {
        int boardSize = this.getBoardSize();
        int secondScore = 0;

        for (int i = 0; i < boardSize; i++)
        {
            for (int j = 0; j < boardSize; j++)
            {
                if (this.gameBoard.getCellStatus(new Pair(i, j)).getColor()
                        .toString().equals(this.SecondColor.toString()))
                {
                    secondScore++;
                }
            }
        }
        return secondScore;
    }

    /**
     * Pure virtual function to check if the move is valid.
     *
     * @param p         wanted cell.
     * @param scanD     scan direction.
     * @param opponentP opponent's player color.
     * @param player:   player's color.
     * @return true/false.
     */
    abstract public boolean validMove(Pair p, ScanDirection scanD, Color opponentP, Color player);

    /**
     * Pure virtual function to update all the possible moves.
     *
     * @param pairArr inputted array to update.
     * @param index   number of moves.
     * @param player  player's color.
     */
    abstract public int possibleMoves(Pair pairArr[], int index, Color player);

    /**
     * Pure virtual function to check if the cell is a possible move.
     *
     * @param p         inputted pair.
     * @param opponentP opponent's player color.
     * @param player:   player's color.
     * @return true/false.
     */
    abstract public boolean checkCell(Pair p, Color opponentP, Color player);

    /**
     * Pure virtual function to flip the right cells after the user played it's turn.
     *
     * @param p         inputted pair (wanted move).
     * @param opponentP opponent's player color.
     * @param player:   player's color.
     */
    abstract public void flipCell(Pair p, Color opponentP, Color player);

    /**
     * Virtual method to checks the users input validation (right format & picking a move from the possible moves)
     *
     * @param p     wanted users move.
     * @param arr   array of possible moves.
     * @param count number of possible moves.
     * @return true/false for good/bad format.
     */
    abstract public boolean checkInput(Pair p, Pair arr[], int count);

    /**
     * Enum for the board scanning directions.
     */
    enum ScanDirection {
        NorthWest, North, NorthEast, West, East, SouthWest, South, SouthEast
    }

    /**
     * Enum for game winning.
     */
    enum GameWinner {
        Draw, BlackWon, WhiteWon
    }
}
