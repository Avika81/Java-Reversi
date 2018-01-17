package ReversiBase;

import javafx.scene.paint.Color;
/**
  *game class is a class containing all the game wanted functions for
  * logic and so on.
  **/
public class Game {

    private boolean isPlayer1TurnNow;
    private Player Player1;
    private Player Player2;
    private Display MyDisplay;
    private GameLogic MyGameLogic;

    /**
     * Constructor for HumanPlayersOnComputer class.
     * @param MyGameLogic inputted pointer to game logic.
     */
    public Game(GameLogic MyGameLogic, Display MyDisplay, Color player1Color, Color player2Color) {
        this.Player1 = new HumanPlayer(true, player1Color);
        this.Player2 = new HumanPlayer(false, player2Color);
        this.isPlayer1TurnNow = true;
        this.MyGameLogic = MyGameLogic;
        this.MyDisplay = MyDisplay;
    }

    /**
     * This method runs the game.
     */
    public void run() {
        boolean noMoreActionsB = false;
        boolean noMoreActionW = false;
        int moves;
        while (!this.MyGameLogic.checkAndAnnounceFinish(noMoreActionsB, noMoreActionW, MyDisplay)) {
            //new pait and strings each iteration of the loop:
            Pair userInput;
            String move, moveB;
            moves = 0;
            //new
            Pair pArr[] = new Pair[this.MyGameLogic.getBoardSize() * this.MyGameLogic.getBoardSize() + 1];
            MyDisplay.printBoard(this.MyGameLogic.getBoard());
            if (this.isPlayer1TurnNow) {
                if (this.MyGameLogic.checkAndAnnounceFinish(noMoreActionsB, noMoreActionW, MyDisplay)) {
                    return;
                }
                moves = this.MyGameLogic.possibleMoves(pArr, moves, this.Player1.getColor());
                if (moves == 0) {
                    this.Player1.noMove(this.MyDisplay);
                    noMoreActionsB = true;
                } else {
                    do {
                        userInput = player1.getMove(pArr, moves, player2.getColor(), MyDisplay);
                    } while (!this.MyGameLogic.checkInput(userInput, pArr, moves));
                    this.MyGameLogic.flipCell(userInput, player2.getColor(), player1.getColor());
                    //MyDisplay.printPair(new ReversiBase.Pair(userInput.getRow() - 1, userInput.getCol() - 1));
                    noMoreActionsB = false;
                }
                this.isPlayer1TurnNow = false;
            } else {
                moves = this.MyGameLogic.possibleMoves(pArr, moves, player2.getColor());

                if (moves == 0) {
                    this.Player2.noMove(this.MyDisplay);
                    noMoreActionW = true;
                }
                else {
                    do {
                        userInput = player2.getMove(pArr, moves, player1.getColor(), MyDisplay);
                    } while (!this.MyGameLogic.checkInput(userInput, pArr, moves));
                    this.MyGameLogic.flipCell(userInput, player1.getColor(), player2.getColor());
                    //MyDisplay.printPair(new ReversiBase.Pair(userInput.getRow() - 1, userInput.getCol() - 1));
                    noMoreActionW = false;
                }
                this.isPlayer1TurnNow = true;
            }
        }
    }
}
