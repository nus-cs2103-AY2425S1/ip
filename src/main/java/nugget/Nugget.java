package nugget;

import nugget.gui.Main;
import nugget.command.Command;
import nugget.exception.NuggetException;

/**
 * The main class for the Nugget application, which handles task management.
 */
public class Nugget {
    private Ui ui;
    private Storage storage;
    private TaskList tasks;
    private Parser parser;
    private Main gui;  // Reference to the JavaFX GUI

    /**
     * Constructs a Nugget instance with the specified file path for storage and GUI reference.
     *
     * @param filePath The file path where tasks will be loaded and saved.
     * @param gui The reference to the Main JavaFX GUI for output handling.
     */
    public Nugget(String filePath, Main gui) {
        this.gui = gui;
        ui = new Ui(gui);  // Pass GUI to UI
        storage = new Storage(filePath);
        tasks = new TaskList(storage.loadTasks());
        parser = new Parser(tasks);
    }

    /**
     * Initializes the Nugget application, printing the intro message.
     */
    public void start() {
        ui.printIntro();
    }

    /**
     * Handles a single input from the GUI and executes the appropriate command.
     *
     * @param input The user's input command.
     */
    public void handleInput(String input) {
        try {
            if (input.equals("bye")) {
                ui.printEnd();
                return;
            }
            Command c = parser.parse(input);
            c.execute(tasks, ui, storage);
        } catch (NuggetException e) {
            ui.showError(e.getMessage());
        }
    }
}
