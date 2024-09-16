package moody;

import moody.commands.Command;
import moody.exceptions.InvalidCommandException;
import moody.exceptions.TaskInputException;
import moody.parser.Parser;
import moody.storage.Storage;
import moody.tasks.TaskList;
import moody.ui.Ui;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Manages the main application for handling tasks, including initializing the task list,
 * interacting with storage, and processing user commands.
 */
public class Moody {
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

    /**
     * Creates a Moody instance with the specified file path for storage.
     *
     * @param filePath The file path to load the tasks from. If the file is not found,
     *                 an empty task list will be created.
     */
    public Moody(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);

        TaskList loadedTasks = null;

        try {
            loadedTasks = new TaskList(storage.load());
            ui.showLine();
            ui.showLoadingSuccess();
        } catch (FileNotFoundException e) {
            ui.showError(e.getMessage());
        }

        this.tasks = loadedTasks != null ? loadedTasks : new TaskList();
    }

    /**
     * Runs the application loop.
     * Displays the welcome message, reads commands from the user, parses and executes them,
     * and handles any errors. The loop continues until an exit command is encountered.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;

        while (!isExit) {
            String fullCommand = ui.readCommand();

            try {
                Command command = Parser.parse(fullCommand);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (InvalidCommandException | IOException | TaskInputException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    public String getResponse(String input) {
        try {
            return Parser.parse(input).execute(tasks, ui, storage);
        } catch (InvalidCommandException | TaskInputException e) {
            return e.getMessage();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    public static void main(String[] args) {
        new Moody("./data/moody.txt").run();
    }
}
