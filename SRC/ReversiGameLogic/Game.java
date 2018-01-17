
package ReversiBase;


import javafx.scene.paint.Color;

public class Game {

    private boolean isPlayer1TurnNow;
    private Player Player1;
    private Player Player2;
    private Display display;
    private GameLogic gameLogic;

    /**
     * Constructor for HumanPlayersOnComputer class.
     *
     * @param gameLogic inputted pointer to game logic.
     */
    public Game(GameLogic gameLogic, Display display, Color player1Color, Color player2Color) {
        this.gameLogic = gameLogic;
        this.display = display;
        this.isPlayer1TurnNow = true;
        this.Player1 = new HumanPlayer(true, player1Color);
        this.Player2 = new HumanPlayer(false, player2Color);

    }

    /**
     * This method runs the game.
     */
    public void run() {
        boolean noMoreActionsB = false;
        boolean noMoreActionW = false;
        int moves;
        while (!this.gameLogic.checkAndAnnounceFinish(noMoreActionsB, noMoreActionW, display)) {
            String move, moveB;
            Pair userInput;
            moves = 0;
            //new
            Pair pArr[] = new Pair[this.gameLogic.getBoardSize() * this.gameLogic.getBoardSize() + 1];
            display.printBoard(this.gameLogic.getBoard());
            if (this.isPlayer1TurnNow) {
                if (this.gameLogic.checkAndAnnounceFinish(noMoreActionsB, noMoreActionW, display)) {
                    return;
                }
                moves = this.gameLogic.possibleMoves(pArr, moves, this.Player1.getColor());
                if (moves == 0) {
                    this.Player1.noMove(this.display);
                    noMoreActionsB = true;
                } else {
                    do {
                        userInput = player1.getMove(pArr, moves, player2.getColor(), display);
                    } while (!this.gameLogic.checkInput(userInput, pArr, moves));
                    this.gameLogic.flipCell(userInput, player2.getColor(), player1.getColor());
                    //display.printPair(new ReversiBase.Pair(userInput.getRow() - 1, userInput.getCol() - 1));
                    noMoreActionsB = false;
                }
                this.isPlayer1TurnNow = false;
            } else {
                moves = this.gameLogic.possibleMoves(pArr, moves, player2.getColor());
                if (moves == 0) {
                    this.Player2.noMove(this.display);
                    noMoreActionW = true;
                } else {
                    do {
                        userInput = player2.getMove(pArr, moves, player1.getColor(), display);
                    } while (!this.gameLogic.checkInput(userInput, pArr, moves));
                    this.gameLogic.flipCell(userInput, player1.getColor(), player2.getColor());
                    //display.printPair(new ReversiBase.Pair(userInput.getRow() - 1, userInput.getCol() - 1));
                    noMoreActionW = false;
                }
                this.isPlayer1TurnNow = true;
            }
        }
    }
}
