
package ReversiBase;


import javafx.scene.paint.Color;

public class Game {

    private boolean player1Turn;
    private Player player1;
    private Player player2;
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
        this.player1Turn = true;
        this.player1 = new HumanPlayer(true, player1Color);
        this.player2 = new HumanPlayer(false, player2Color);

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
            if (this.player1Turn) {
                if (this.gameLogic.checkAndAnnounceFinish(noMoreActionsB, noMoreActionW, display)) {
                    return;
                }
                moves = this.gameLogic.possibleMoves(pArr, moves, this.player1.getColor());
                if (moves == 0) {
                    this.player1.noMove(this.display);
                    noMoreActionsB = true;
                } else {
                    do {
                        userInput = player1.getMove(pArr, moves, player2.getColor(), display);
                    } while (!this.gameLogic.checkInput(userInput, pArr, moves));
                    this.gameLogic.flipCell(userInput, player2.getColor(), player1.getColor());
                    //display.printPair(new ReversiBase.Pair(userInput.getRow() - 1, userInput.getCol() - 1));
                    noMoreActionsB = false;
                }
                this.player1Turn = false;
            } else {
                moves = this.gameLogic.possibleMoves(pArr, moves, player2.getColor());
                if (moves == 0) {
                    this.player2.noMove(this.display);
                    noMoreActionW = true;
                } else {
                    do {
                        userInput = player2.getMove(pArr, moves, player1.getColor(), display);
                    } while (!this.gameLogic.checkInput(userInput, pArr, moves));
                    this.gameLogic.flipCell(userInput, player1.getColor(), player2.getColor());
                    //display.printPair(new ReversiBase.Pair(userInput.getRow() - 1, userInput.getCol() - 1));
                    noMoreActionW = false;
                }
                this.player1Turn = true;
            }
        }
    }
}
