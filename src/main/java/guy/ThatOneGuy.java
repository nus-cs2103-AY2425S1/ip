package guy;

import java.util.Scanner;

import guy.exception.GuyException;
import guy.gui.Launcher;
import guy.parser.Parser;
import guy.storage.Storage;
import guy.ui.Ui;

/**
 * Main class of the (rather annoyed) ThatOneGuy application.
 * It initialises a list of tasks, the UI, and storage, and handles the main logic.
 */
public class ThatOneGuy {
    private Ui ui;
    private Storage storage;
    private Parser parser;
    private boolean isRunning;

    /**
     * Constructs a ThatOneGuy object.
     * Does so by initialising the ui/task list/storage, then reads data from storage.
     */
    public ThatOneGuy() {
        isRunning = true;
        ui = new Ui();
        storage = new Storage();
        parser = new Parser(new Scanner(System.in));
        storage.readData();
    }

    /**
     * Starts the ThatOneGuy application.
     *
     * @param args command-line arguments, currently unused
     */
    public static void main(String... args) {
        if (args.length > 0 && args[0].equals("--cli")) {
            ThatOneGuy guy = new ThatOneGuy();
            guy.ui.greet();
            guy.keepGoing();
            guy.ui.bye();
        } else {
            Launcher.main(args);
        }
    }
    public String getGreeting() {
        return ui.getGreeting();
    }

    public String getResponse(String input) {
        try {
            String response = parser.handleGuiInput(input);
            if (response.equals("Whatever. Hope you never come back.")) {
                isRunning = false;
            }
            return response;
        } catch (GuyException e) {
            return e.getMessage();
        }
    }

    /**
     * Tells the application to continue reading user inputs, until the user requests to quit.
     */
    private void keepGoing() {
        Parser p = new Parser(new Scanner(System.in));
        while (isRunning) {
            isRunning = p.handleCliInput();
        }
    }

    /**
     * Accessor function to check if ThatOneGuy is running.
     */
    public boolean isRunning() {
        return isRunning;
    }
}
