package nugget;

import nugget.command.Command;
import nugget.exception.NuggetException;
import nugget.gui.ChatUiController;

/**
 * The main logic handler for the Nugget task tracker application.
 * It manages the user input, interacts with the TaskList, Storage, and UI components,
 * and handles the command execution.
 */
public class Nugget {
    private Ui ui;
    private Storage storage;
    private TaskList tasks;
    private Parser parser;
    private ChatUiController gui;

    /**
     * Constructor for Nugget.
     *
     * @param filePath The path of the file where tasks are stored.
     * @param gui The ChatUiController to interact with the user interface.
     */
    public Nugget(String filePath, ChatUiController gui) {
        this.gui = gui;
        ui = new Ui(gui);
        storage = new Storage(filePath);
        tasks = new TaskList(storage.loadTasks());
        parser = new Parser(tasks);
    }

    /**
     * Starts the application by printing the introduction.
     */
    public void start() {
        ui.printIntro();
    }

    /**
     * Handles the user input, processes the command, and updates the UI.
     *
     * @param input The input from the user.
     */
    public void handleInput(String input) {
        try {
            Command c = parser.parse(input);
            c.execute(tasks, ui, storage);
        } catch (NuggetException e) {
            ui.showError(e.getMessage());
        }
    }
}
