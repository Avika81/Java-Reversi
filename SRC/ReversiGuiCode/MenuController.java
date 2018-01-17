/*
 * Tomer Grady 205660863
 * Raz Shenkman 311130777
 */
package ReversiGUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * This class sets the menu controller.
 */
public class MenuController {

    @FXML
    private Button startButton;
    @FXML
    private Button Settings;
    @FXML
    private Button ExitButton;

    /**
     * This method starts the game.
     */
    @FXML
    protected void startGame() {
        try {
            Stage stage = (Stage) startButton.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Game.fxml"));
            HBox root = (HBox) loader.load();
            Scene reversiGameScene = new Scene(root, 800, 605);
            stage.setScene(reversiGameScene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method exits the menu and closes the game.
     */
    @FXML
    protected void exitGame() {
        Stage stage = (Stage) ExitButton.getScene().getWindow();
        stage.close();
    }

    /**
     * This method starts the settings.
     */
    @FXML
    protected void settings() {
        try {
            Stage stage = (Stage) Settings.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Settings.fxml"));
            VBox root = (VBox) loader.load();
            loader.setController(new SettingsController());
            Scene settingsScene = new Scene(root, 650, 600);
            stage.setScene(settingsScene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
