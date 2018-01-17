package ReversiGUI;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import javafx.scene.paint.Color;

/**
 * This class parses the settings file.
 */
public class SettingsParser {
    private static final int LINES = 4;
    String filePath = "settings.txt";
    private String Player1Color;
    private String Player2Color;
    private String startingPlayer;
    private Integer boardSize;

    /*
    Empty constructor for setting parser.
     */
    public SettingsParser() {
    }

    /**
     * This method parse the settings file.
     */
    public void parseSettingsFile() {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.createNewFile();
                initializeDefaultValues();
                writeNewSettings(boardSize, startingPlayer, Player1Color, Player2Color);
                return;
            }
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            for (int i = 0; i < LINES; i++) {
                line = bufferedReader.readLine();
                if (i == 0)
                    this.boardSize = Integer.parseInt(line);
                if (i == 1)
                    this.startingPlayer = line;
                if (i == 2)
                    this.Player1Color = line;
                if (i == 3)
                    this.Player2Color = line;
            }
            fileReader.close();
        } catch (Exception e) {
            System.out.println("Can't parse settings from file, using the default settings");
            // using the default values
            initializeDefaultValues();
        }
    }

    /**
     * This method changes the settings file.
     *
     * @param boardSize      new board size.
     * @param startingPlayer new starting player.
     * @param player1C       new player 1 color.
     * @param player2C       new player 2 color.
     */
    public void writeNewSettings(Integer boardSize, String startingPlayer, String player1C, String player2C) {
        try {
            File file = new File(this.filePath);

            if (!file.exists())
                file.createNewFile();

            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(boardSize.toString());
            bufferedWriter.newLine();
            bufferedWriter.write(startingPlayer);
            bufferedWriter.newLine();
            bufferedWriter.write(player1C);
            bufferedWriter.newLine();
            bufferedWriter.write(player2C);
            bufferedWriter.close();
        } catch (Exception e) {
            System.out.println("Can't write settings to file!");
        }
    }

    /**
     * This method initializes a new settings file from default values.
     */
    private void initializeDefaultValues() {
        this.boardSize = 8;
        this.startingPlayer = "player1";
        this.Player1Color = Color.BLACK.toString();
        this.Player2Color = Color.GRAY.toString();
    }

    /**
     * This method returns the color of the first player.
     *
     * @return color of the first player.
     */
    public String getPlayer1Color() {
        return Player1Color;
    }

    /**
     * This method returns the color of the second player.
     *
     * @return color of the second player.
     */
    public String getPlayer2Color() {
        return Player2Color;
    }

    /**
     * This method returns who is the starting player.
     *
     * @return who is the starting player.
     */
    public String getStartingPlayer() {
        return this.startingPlayer;
    }

    /**
     * This method returns the board size.
     *
     * @return the board size.
     */
    public Integer getBoardSize() {
        return this.boardSize;
    }
}
