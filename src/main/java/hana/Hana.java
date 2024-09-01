package hana;

import java.io.IOException;

import hana.task.TaskList;

/**
 * Entry point for the Hana application.
 * Manages the initialization and execution of the program.
 */
public class Hana {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Initializes the Hana application with the given file path.
     * Loads the task list from the specified file.
     *
     * @param filePath The file path where the tasks are stored.
     */
    public Hana(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the Hana application, which listens for user input and executes commands.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (HanaException | IOException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    /**
     * Main method to start the Hana application.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        new Hana("data/hana.txt").run();
    }
}
