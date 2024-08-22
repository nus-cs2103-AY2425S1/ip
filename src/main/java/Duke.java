import parser.Parser;
import storage.Storage;
import tasks.TaskList;
import ui.Ui;

import java.util.Scanner;

/**
 * The Duke class is the main class of the Duke application.
 * It initializes the UI, storage, and task list, and handles the main program loop.
 */
public class Duke {
    private TaskList taskList;
    private final Ui ui;
    private final Storage storage;

    /**
     * Constructs a new Duke object.
     * Initializes the UI, storage, and task list, and loads data from storage.
     */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage();
        this.taskList = new TaskList();
        this.storage.readData();
    }

    /**
     * The main method that starts the Duke application.
     *
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.ui.startup();
        duke.continueReading();
        duke.ui.shutdown();
    }

    /**
     * Continues reading user input and handling it until the user decides to exit.
     */
    private void continueReading() {
        Parser parser = new Parser(new Scanner(System.in));
        boolean running = true;
        while (running) {
            running = parser.handleUserInput();
        }
    }
}