
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
    private String Player1ColorString;
    private String Player2ColorString;
    private String theStartingPlayer;
    private Integer theSizeOfBoard;

    /*
    Empty constructor for setting parser.
     */
    public SettingsParser() {
        //nothing happens
    }

    /**
     * This method parse the settings file.
     */
    public void parseSettingsFile() {
        try {
            File tFile = new File(filePath);
            if (!tFile.exists()) {
                tFile.createNewFile();
                initializeDefaultValues();
                writeNewSettings(theSizeOfBoard, theStartingPlayer, Player1ColorString, Player2ColorString);
                return;
            }
            FileReader fileReader = new FileReader(tFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            for (int i = 0; i < LINES; i++) {
                line = bufferedReader.readLine();
                if (i == 0)
                    this.theSizeOfBoard = Integer.parseInt(line);
                if (i == 1)
                    this.theStartingPlayer = line;
                if (i == 2)
                    this.Player1ColorString = line;
                if (i == 3)
                    this.Player2ColorString = line;
            }
            fileReader.close();
        } catch (Exception thrownExeption) {
            //throwing the exeption happend in the code
            System.out.println("Can't parse settings from file, using the default settings");
            // using the default values
            initializeDefaultValues();
        }
    }

    /**
     * This method changes the settings file.
     *
     * @param theSizeOfBoard      new board size.
     * @param theStartingPlayer new starting player.
     * @param player1C       new player 1 color.
     * @param player2C       new player 2 color.
     */
    public void writeNewSettings(Integer theSizeOfBoard, String theStartingPlayer, String player1C, String player2C) {
        try {
            File file = new File(this.filePath);

            if (!file.exists())
                file.createNewFile();

            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(theSizeOfBoard.toString());
            bufferedWriter.newLine();
            bufferedWriter.write(theStartingPlayer);
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
     * (the size of board is 8 and the starting is player 1)
     */
    private void initializeDefaultValues() {
        this.theSizeOfBoard = 8;
        this.theStartingPlayer = "player1";
        this.Player1ColorString = Color.BLACK.toString();
        this.Player2ColorString = Color.GRAY.toString();
    }

    /**
     * standart getter
     */
    public String getPlayer1Color() {
        return Player1ColorString;
    }

    /**
     * standart getter
    public String getPlayer2Color() {
        return Player2ColorString;
    }

    /**
     * standart getter
     */
    public String getStartingPlayer() {
        return this.theStartingPlayer;
    }

    /**
     * standart getter
     */
    public Integer getBoardSize() {
        return this.theSizeOfBoard;
    }
}
