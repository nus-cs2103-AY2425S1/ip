package garfield;

import garfield.commands.Command;
import garfield.exceptions.GarfieldException;
import garfield.parser.Parser;
import garfield.storage.Storage;
import garfield.tasks.TaskList;
import garfield.ui.Ui;

/**
 * The Garfield class is the main entry point for the Garfield chatbot application.
 * It initializes the necessary components (UI, Storage, TaskList) and handles
 * the main event loop for user interaction.
 */
public class Garfield {
    private Ui ui;
    private Storage storage;
    private TaskList taskList;

    /**
     * Constructs a new Garfield instance.
     *
     * @param saveFilePath The file path where tasks are saved and loaded from.
     */
    public Garfield(String saveFilePath) {
        this.ui = new Ui();
        this.storage = new Storage(saveFilePath);
        this.taskList = new TaskList(this.storage.load());
    }

    /**
     * Starts the Garfield chatbot, showing a greeting message, processing user
     * commands in a loop, and showing an ending message before exiting.
     */
    public void run() {
        this.ui.showGreeting();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } catch (GarfieldException e) {
                this.ui.showError(e.getMessage());
            }
        }
        this.ui.showEnding();
    }

    /**
     * The main method, which serves as the entry point of the Garfield application.
     *
     * @param args Command-line arguments passed to the application (not used).
     */
    public static void main(String[] args) {
        new Garfield("./data/save.txt").run();
    }

    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(taskList, storage);
        } catch (GarfieldException e) {
            return e.getMessage();
        }
    }
}
