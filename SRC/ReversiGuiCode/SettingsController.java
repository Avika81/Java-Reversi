/*
 * Tomer Grady 205660863
 * Raz Shenkman 311130777
 */
package ReversiGUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller of the settings scene.
 */
public class SettingsController implements Initializable {
    @FXML
    private ColorPicker Player1Color;

    @FXML
    private ColorPicker Player2Color;

    @FXML
    private ChoiceBox<String> WhoStarts;

    @FXML
    private ChoiceBox<Integer> BoardSize;
    @FXML
    private Button MenuButton;
    /**
     * This method returns to the main menu.
     *
     * @throws IOException if failed.
     */
    @FXML
    protected void mainMenu() throws IOException {
        try {
            String writeStartingP;
            SettingsParser parser = new SettingsParser();
            if (WhoStarts.getValue().equals("Player 1")) {
                writeStartingP = "player1";
            } else {
                writeStartingP = "player2";
            }
            parser.writeNewSettings(BoardSize.getValue(), writeStartingP, Player1Color.getValue().toString(),
                    Player2Color.getValue().toString());
            Stage primaryStage = (Stage) MenuButton.getScene().getWindow();
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


    @Override
    /**
     * Initialize the settings.
     */
    public void initialize(URL location, ResourceBundle resources) {

        WhoStarts.getItems().addAll("Player 1", "Player 2");
        for (int i = 4; i < 21; i++) {
            BoardSize.getItems().add(i);
        }
        parseSettingsFile();
    }

    /**
     * This method initialize the values in the setting to show the current values.
     */
    private void parseSettingsFile() {
        SettingsParser parser = new SettingsParser();
        parser.parseSettingsFile();
        BoardSize.setValue(parser.getBoardSize());
        Player1Color.setValue(Color.web(parser.getPlayer1Color()));
        Player2Color.setValue(Color.web(parser.getPlayer2Color()));
        if (parser.getStartingPlayer().equals("player1")) {
            WhoStarts.setValue("Player 1");
        } else {
            WhoStarts.setValue("Player 2");
        }
    }
}
