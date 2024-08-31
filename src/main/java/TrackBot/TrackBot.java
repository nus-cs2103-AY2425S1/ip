package TrackBot;

import TrackBot.commands.Command;
import TrackBot.task.TrackList;
import TrackBot.ui.Parser;
import TrackBot.ui.Ui;

import java.io.IOException;
import java.util.Scanner;

/**
 * The main class for TrackBot.
 * This class initializes the storage, tasks list, parser and UI.
 * It also contains the main loop to handle user commands.
 */
public class TrackBot {
    private TrackBotStorage storage;
    private TrackList trackList;
    private final Ui ui;
    private final Parser parser;

    /**
     * Constructs a TrackBot object and initializes storage, tasks list, UI, and parser.
     *
     * @param filePath The file path to the storage file.
     */
    public TrackBot(String filePath) {
        ui = new Ui();
        parser = new Parser();
        try {
            storage = new TrackBotStorage(filePath);
            trackList = new TrackList(storage);
        } catch (IOException e) {
            System.out.println("File not found");
        }
        if (trackList == null) {
            System.out.println("Failed to initialize TrackList.");
        }
    }


    /**
     * Runs TrackBot, stays in the loop until user input exit command.
     * Checks user input and executes command.
     */
    public void run() {
        Scanner scanner = new Scanner(System.in);
        ui.showWelcome();
        boolean isExit = false;

        while (!isExit) {
            String userInput = scanner.nextLine();
            try {
                Command command = parser.parse(userInput);
                command.execute(trackList, ui, storage);
                isExit = command.isExit();
            } catch (TrackBotException e) {
                System.out.println(e.getMessage());
            }
        }

        scanner.close();
    }
    
    public static void main(String[] args) {
        String filePath = "src/main/java/TrackBot/data/TrackBot.txt";
        new TrackBot(filePath).run();
    }
}
