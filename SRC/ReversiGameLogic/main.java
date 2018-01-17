
package ReversiBase;

import javafx.scene.paint.Color;

/**
 * The main of the console game.
 */
public class main {
    public static void consoleMain(String[] args) {
        Game game;
        Display display = new ConsoleDisplay();
        Board board = new Board(8, Color.BLACK, Color.WHITE);
        GameLogic gameLogic = new RegularGameLogic(board, Color.BLACK, Color.WHITE);
        game = new Game(gameLogic, display, Color.BLACK, Color.WHITE);
        game.run();

    }
}
