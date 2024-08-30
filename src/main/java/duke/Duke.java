package duke;

import java.io.IOException;

import duke.command.Command;
import duke.exception.EmptyDescriptionException;
import duke.exception.UnknownCommandException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents the main class of the Duke application, which manages tasks
 * and handles user interactions through a command-line interface.
 */
public class Duke {
    private static final String FILE_PATH = "./duke/data/duke.txt";
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a Duke object, initializing the user interface, storage, and task list.
     * Attempts to load tasks from a specified file path.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage(FILE_PATH);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showError("Error loading tasks: " + e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Runs the main loop of the Duke application, handling user input and executing commands
     * until the user decides to exit.
     */
    public void run() {
        ui.showWelcome();

        boolean isRunning = true;
        while (isRunning) {
            try {
                String input = ui.readCommand();
                Command command = Parser.parse(input);
                command.execute(tasks, ui, storage);
                isRunning = !command.isExit();
            } catch (EmptyDescriptionException | UnknownCommandException e) {
                ui.showError(e.getMessage());
            } catch (IOException e) {
                ui.showError("An error occurred while saving your tasks.");
            }
        }
    }

    /**
     * The main method that launches the Duke application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        new Duke().run();
    }
}