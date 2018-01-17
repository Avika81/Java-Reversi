/*
 * Tomer Grady 205660863
 * Raz Shenkman 311130777
 */
package ReversiGUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * This class starts the menu.
 */
public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * THis method starts the menu controller.
     *
     * @param primaryStage main stage.
     */
    @Override
    public void start(Stage primaryStage) {
        try {
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
}