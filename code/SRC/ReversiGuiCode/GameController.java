package ReversiGUI;


//tons of imports for the gui:
import ReversiBase.Board;
import ReversiBase.GameLogic;
import ReversiBase.Pair;
import ReversiBase.RegularGameLogic;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements Initializable {
    private Board board;
    private GameLogic gameLogic;
    private boolean isPlayer1;
    private String player1ColorString;
    private String player2ColorString;
    private boolean noMoreActionsP1;
    private boolean noMoreActionsP2;
    @FXML
    private GuiBoard guiBoard;
    @FXML
    private HBox root;
    @FXML
    private Label message;
    @FXML
    private Label currentPlayer;
    @FXML
    private Label player1Score;
    @FXML
    private Label player2Score;
    @FXML
    private Label extraMessage;
    @FXML
    private Button quit;
    @FXML
    private Circle colorPlayer;
    private boolean firstIter = true;

    /**
     * This method loads the FXML (to return back to the menu).
     *
     * @param fxml   inputted fxml.
     * @param width  inputted width.
     * @param height inputted height.
     * @param event  event if needed.
     */
    @FXML
    protected void loadFXML(String fxml, int width, int height, ActionEvent event) {
        try {
            Stage primaryStage = (Stage) this.quit.getScene().getWindow();
            GridPane root = (GridPane) FXMLLoader.load(getClass().getResource("MenuControllerFXML.fxml"));
            root.setAlignment(Pos.CENTER);
            Scene scene = new Scene(root, 650, 600);
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            primaryStage.setTitle("Reversi");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Perform a single move of a player (after a mouse click on the board has been made)
     *
     * @param move the move of the player
     */
    @FXML
    protected void singleMove(Pair move) {
        if (checkFinish()) {
            return;
        }
        int moves = 0;
        Pair pArr[] = new Pair[this.gameLogic.getBoardSize() * this.gameLogic.getBoardSize() + 1];
        if (this.isPlayer1) {
            moves = gameLogic.possibleMoves(pArr, moves, Color.web(this.player1ColorString));
        } else {
            moves = gameLogic.possibleMoves(pArr, moves, Color.web(this.player2ColorString));
        }

        boolean validMove = this.gameLogic.checkInput(move, pArr, moves);
        if (validMove) {
            if (this.isPlayer1) {
                this.gameLogic.flipCell(move, Color.web(this.player2ColorString), Color.web(this.player1ColorString));
                noMoreActionsP1 = false;
                this.isPlayer1 = false;
                if (this.checkFinish()) {
                    return;
                }
                int moves2 = 0;
                pArr = new Pair[this.gameLogic.getBoardSize() * this.gameLogic.getBoardSize() + 1];
                moves2 = gameLogic.possibleMoves(pArr, moves2, Color.web(this.player2ColorString));
                if (moves2 == 0) {
                    message.setText("Player 2:\nYou have\nno more moves!");
                    this.isPlayer1 = true;
                    this.noMoreActionsP2 = true;
                    int moves3 = 0;
                    pArr = new Pair[this.gameLogic.getBoardSize() * this.gameLogic.getBoardSize() + 1];
                    moves3 = gameLogic.possibleMoves(pArr, moves3, Color.web(this.player1ColorString));
                    if (moves3 == 0) {
                        this.noMoreActionsP1 = true;
                    }
                    if (this.checkFinish()) {
                        return;
                    }
                    currentPlayer.setText("\nCurrent Player: Player 1");
                    colorPlayer.setFill(Color.web(this.player1ColorString));

                    message.setText("Player 1:\nIt's your move!");
                    guiBoard.setBoard(this.board);
                    guiBoard.draw();
                    int firstScore = gameLogic.getFirstPlayerScore();
                    int secondScore = gameLogic.getSecondPlayerScore();
                    player1Score.setText("Player 1 score: " + firstScore);
                    player2Score.setText("Player 2 score: " + secondScore);
                } else {
                    currentPlayer.setText("\nCurrent Player: Player 2");
                    colorPlayer.setFill(Color.web(this.player2ColorString));
                    message.setText("Player 2:\nIt's your move!");
                    guiBoard.setBoard(this.board);
                    guiBoard.draw();
                    int firstScore = gameLogic.getFirstPlayerScore();
                    int secondScore = gameLogic.getSecondPlayerScore();
                    player1Score.setText("Player 1 score: " + firstScore);
                    player2Score.setText("Player 2 score: " + secondScore);
                    if (this.checkFinish()) {
                        return;
                    }
                }
            } else {
                this.gameLogic.flipCell(move, Color.web(this.player1ColorString), Color.web(this.player2ColorString));
                noMoreActionsP2 = false;
                if (this.checkFinish()) {
                    return;
                }
                this.isPlayer1 = true;
                int moves2 = 0;
                pArr = new Pair[this.gameLogic.getBoardSize() * this.gameLogic.getBoardSize() + 1];
                moves2 = gameLogic.possibleMoves(pArr, moves2, Color.web(this.player1ColorString));
                if (moves2 == 0) {
                    message.setText("Player 1:\nYou have\nno more moves!");
                    this.isPlayer1 = false;
                    this.noMoreActionsP1 = true;
                    int moves3 = 0;
                    pArr = new Pair[this.gameLogic.getBoardSize() * this.gameLogic.getBoardSize() + 1];
                    moves3 = gameLogic.possibleMoves(pArr, moves3, Color.web(this.player2ColorString));
                    if (moves3 == 0) {
                        this.noMoreActionsP2 = true;
                    }
                    if (this.checkFinish()) {
                        return;
                    }
                    currentPlayer.setText("\nCurrent Player: Player 2");
                    message.setText("Player 2:\nIt's your move!");
                    colorPlayer.setFill(Color.web(this.player2ColorString));
                    guiBoard.setBoard(this.board);
                    guiBoard.draw();
                    int firstScore = gameLogic.getFirstPlayerScore();
                    int secondScore = gameLogic.getSecondPlayerScore();
                    player1Score.setText("Player 1 score: " + firstScore);
                    player2Score.setText("Player 2 score: " + secondScore);
                } else {
                    currentPlayer.setText("\nCurrent Player: Player 1");
                    message.setText("Player 1:\nIt's your move!");
                    colorPlayer.setFill(Color.web(this.player1ColorString));

                    guiBoard.setBoard(this.board);
                    guiBoard.draw();
                    int firstScore = gameLogic.getFirstPlayerScore();
                    int secondScore = gameLogic.getSecondPlayerScore();
                    player1Score.setText("Player 1 score: " + firstScore);
                    player2Score.setText("Player 2 score: " + secondScore);
                    if (this.checkFinish()) {
                        return;
                    }
                }
            }
        } else {
            if (moves == 0) {
                if (this.isPlayer1) {
                    message.setText("Player 1:\nYou have\nno more moves!");
                    this.isPlayer1 = false;
                } else {
                    message.setText("Player 2:\nYou have\nno more moves!");
                    this.isPlayer1 = true;
                }
                if (this.isPlayer1) {
                    this.noMoreActionsP1 = true;
                } else {
                    this.noMoreActionsP2 = true;
                }
                if (checkFinish()) {
                    return;
                }
            } else {
                message.setText("That not a valid move");
            }
        }
    }

    /**
     * This method convert a mouse click position to a position in the board.
     *
     * @param x coordinate of the click.
     * @param y coordinate of the click.
     * @return the new pair
     */
    private Pair convertClickToPair(double x, double y) {
        double cellHight = this.guiBoard.getCellHight();
        double cellWidth = this.guiBoard.getCellwidth();
        for (int i = 0; i < this.board.getSize(); i++) {
            for (int j = 0; j < this.board.getSize(); j++) {
                if (x >= i * cellWidth && x <= (i + 1) * cellWidth) {
                    if (y >= j * cellHight && y <= (j + 1) * cellHight) {
                        return new Pair(j + 1, i + 1);
                    }
                }
            }
        }
        return new Pair(-1, -1);
    }

    /**
     * Check if the game is over.
     *
     * @return if the game is over.
     */
    private boolean checkFinish() {
        if (this.board.isBoardFull() || (this.noMoreActionsP2 && this.noMoreActionsP1)) {
            currentPlayer.setText("\nCurrent Player: Player 2");
            message.setText("Player 2:\nIt's your move!");
            guiBoard.setBoard(this.board);
            guiBoard.draw();
            int firstScore = gameLogic.getFirstPlayerScore();
            int secondScore = gameLogic.getSecondPlayerScore();
            player1Score.setText("Player 1 score: " + firstScore);
            player2Score.setText("Player 2 score: " + secondScore);
            announceWinner();
            return true;
        }
        return false;
    }

    /**
     * Announce a game winner if the game is finished.
     */
    public void announceWinner() {
        int firstScore = gameLogic.getFirstPlayerScore();
        int secondScore = gameLogic.getSecondPlayerScore();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        VBox root = new VBox();
        Label label = new Label();
        Button quit = new Button("Quit");
        quit.setOnAction(ev -> {
            loadFXML("MenuControllerFXML.fxml", 650, 600, ev);
            stage.close();
        });
        if (firstScore == secondScore) {
            label.setText("Game Over\nIt's a Tie!");
            root.getChildren().addAll(label, quit);
        } else {
            if (secondScore > firstScore) {
                label.setText("Game Over\nSecond player wins!");
                Circle circle = new Circle(10, Color.web(this.player2ColorString));
                root.getChildren().addAll(label, circle, quit);

            } else {
                label.setText("Game Over\nFirst player wins!");
                Circle circle = new Circle(10, Color.web(this.player1ColorString));
                root.getChildren().addAll(label, circle, quit);
            }
        }
        Scene scene = new Scene(root, 200, 100);
        root.setPrefSize(scene.getWidth(), scene.getHeight());
        stage.setScene(scene);
        stage.showAndWait();
    }
}
