package app;

import command.Command;
import fridayException.FridayException;
import parser.Parser;
import storage.Storage;
import task.TaskList;
import ui.Ui;

import java.io.IOException;
import java.time.format.DateTimeParseException;

/**
 * Represents the Friday application that manages tasks for the user.
 */
public class Friday {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    /**
     * Initializes the Friday application by loading tasks from the specified file.
     * If the file cannot be loaded, initializes an empty TaskList and displays an error message.
     *
     * @param filePath The path to the file where tasks are stored.
     */
    public Friday(String filePath) {
        this.storage = new Storage(filePath);
        this.ui = new Ui();
        try {
            this.tasks = new TaskList(storage.load());
        } catch (IOException e) {
            this.ui.showError(e.getMessage());
            this.tasks = new TaskList();
        }
    }

    /**
     * Runs the Friday application, displaying the welcome message and reading commands from the user.
     * The application continues to run until the user enters the "bye" command.
     */
    public void run() {
        ui.showWelcome();
        boolean isEndScanner = false;
        while (!isEndScanner) {
            try {
                String commandRaw = ui.readCommand();
                Command command = Parser.parse(commandRaw);
                command.execute(tasks, ui, storage);
                isEndScanner = command.isEndScanner();
            } catch (FridayException | DateTimeParseException e) {
                if (e instanceof DateTimeParseException) {
                    ui.showError("Please enter a valid date in the format yyyy-mm-dd.");
                } else {
                    ui.showError(e.getMessage());
                }
            }
        }
        ui.closeScanner();
    }

    /**
     * The main method that starts the Friday application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        new Friday("data/fridayTaskList.txt").run();

    }
}