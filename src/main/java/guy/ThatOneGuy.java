package guy;

import java.util.Scanner;

import guy.parser.Parser;
import guy.storage.Storage;
import guy.tasks.TaskManager;
import guy.ui.Ui;

/**
 * Main class of the (rather annoyed) ThatOneGuy application.
 * It initialises a list of tasks, the UI, and storage, and handles the main logic.
 */
public class ThatOneGuy {
    private TaskManager tm;
    private Ui ui;
    private Storage storage;

    /**
     * Constructs a ThatOneGuy object.
     * Does so by initialising the ui/task list/storage, then reads data from storage.
     */
    public ThatOneGuy() {
        ui = new Ui();
        storage = new Storage();
        tm = new TaskManager();
        storage.readData();
    }

    /**
     * Starts the ThatOneGuy application.
     *
     * @param args command-line arguments, currently unused
     */
    public static void main(String[] args) {
        ThatOneGuy guy = new ThatOneGuy();
        guy.ui.greet();
        guy.keepGoing();
        guy.ui.bye();
    }

    /**
     * Tells the application to continue reading user inputs, until the user requests to quit.
     */
    private void keepGoing() {
        Parser p = new Parser(new Scanner(System.in));
        boolean isActive = true;
        while (isActive) {
            isActive = p.cmd();
        }
    }
}
