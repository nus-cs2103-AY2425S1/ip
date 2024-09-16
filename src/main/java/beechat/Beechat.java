package beechat;

import beechat.command.Command;
import beechat.task.TaskList;
import beechat.util.Parser;
import beechat.util.Storage;
import beechat.util.Ui;

import java.lang.String;
import java.io.IOException;

/**
 * Contains the main class of the Beechat chatbot application.
 * Handles the initialization and running of the application.
 * Interact with Beechat using various Commands.
 */
public class Beechat {

    /** Handles storage-related operations such as saving and loading tasks. */
    private Storage storage;

    /** Manages the list of tasks in the application. */
    private TaskList tasks;

    /** Handles user interactions, and displays messages. */
    private Ui ui;

    /**
     * Initializes the Beechat chatbot application with the specified file path.
     * Loads the existing tasks from storage, if any.
     *
     * @param filePath The path to the file where existing tasks are stored.
     */
    public Beechat(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the Beechat chatbot application, executing user commands until a leave command is given.
     */
    public void run() {
        ui.showWelcome();
        boolean isLeave = false;
        while (!isLeave) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isLeave = c.isLeave();
            } catch (Exception e) {
                ui.showError(e.getMessage());
            }
        }
    }

    /**
     * The entry point of the Beechat chatbot application.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        new Beechat("data/beechat.txt").run();
    }
}