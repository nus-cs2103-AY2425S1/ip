package sadcat;

import java.util.Scanner;

import sadcat.exceptions.SadCatException;
import sadcat.gui.Launcher;
import sadcat.parser.Parser;
import sadcat.storage.Storage;
import sadcat.ui.Ui;

/**
 * The SadCat class is the main class of the SadCat application.
 * It initializes the UI, storage, and task list, and handles the main program loop.
 */
public class SadCat {
    private final Ui ui;
    private final Storage storage;
    private boolean isRunning;
    private final Parser parser;

    /**
     * Constructs a new SadCat object.
     * Initializes the UI, storage, and task list, and loads data from storage.
     */
    public SadCat() {
        this.ui = new Ui();
        this.storage = new Storage();
        this.storage.readData();
        this.isRunning = true;
        this.parser = new Parser(new Scanner(System.in));
    }

    /**
     * Starts the SadCat application.
     *
     * @param args Command line arguments
     */
    public static void main(String... args) {
        if (args.length > 0 && args[0].equals("--cli")) {
            SadCat sadcat = new SadCat();
            sadcat.ui.startup();
            sadcat.runCommandLine();
            sadcat.ui.shutdown();
        } else {
            Launcher.main(args);
        }
    }
    public String getGreeting() {
        return ui.getGreeting();
    }

    /**
     * Continues reading user input and handling it until the user decides to exit.
     */
    private void runCommandLine() {
        Parser parser = new Parser(new Scanner(System.in));
        while (isRunning) {
            isRunning = parser.handleUserInput();
        }
    }

    /**
     * Parses a string of GUI input and returns the response.
     * @param input String retrieved from user input into GUI.
     * @return String representing an appropriate response.
     */
    public String getResponse(String input) {
        try {
            String response = parser.handleGuiInput(input);
            assert !response.isEmpty();
            if (response.equals("Bye. Hope to see you again soon!")) {
                isRunning = false;
            }
            return response;
        } catch (SadCatException e) {
            return e.getMessage();
        }
    }

    /**
     * Getter method for SadCat isRunning.
     * @return boolean to show whether the SadCat instance is running
     */
    public boolean isRunning() {
        return isRunning;
    }
}
