package duke;

import java.util.Scanner;

import duke.exceptions.DukeException;
import duke.gui.Launcher;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * The Duke class is the main class of the Duke application.
 * It initializes the UI, storage, and task list, and handles the main program loop.
 */
public class Duke {
    private TaskList taskList;
    private final Ui ui;
    private final Storage storage;
    private boolean isRunning;
    private final Parser parser;

    /**
     * Constructs a new Duke object.
     * Initializes the UI, storage, and task list, and loads data from storage.
     */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage();
        this.taskList = new TaskList();
        this.storage.readData();
        this.isRunning = true;
        this.parser = new Parser(new Scanner(System.in));
    }

    /**
     * The main method that starts the Duke application.
     * Now accepts varargs for potential future extensibility.
     *
     * @param args Command line arguments (currently not used, but could be in the future)
     */
    public static void main(String... args) {
        if (args.length > 0 && args[0].equals("--cli")) {
            Duke duke = new Duke();
            duke.ui.startup();
            duke.runCommandLine();
            duke.ui.shutdown();
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

    public String getResponse(String input) {
        try {
            String response = parser.handleGuiInput(input);
            if (response.equals("Bye. Hope to see you again soon!")) {
                isRunning = false;
            }
            return response;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    /**
     * Getter method for Duke isRunning.
     * @return boolean to show whether the Duke instance is running
     */
    public boolean isRunning() {
        return isRunning;
    }
}
