package bopes;

import java.util.ArrayList;

import bopes.exception.BopesException;
import bopes.task.TaskList;

/**
 * Main class for the Bopes application
 */
public class Bopes {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a Bopes object and initializes the task list and UI.
     * 
     * @param filePath The path to the file used for storage.
     */
    public Bopes(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadTasks());
            ui.showTasks(tasks);
        } catch (BopesException e) {
            ui.showLoadingError();
            tasks = new TaskList(new ArrayList<>());
        }
    }

    /**
     * Starts the Bopes application, reading user commands and processing them.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line
                if (fullCommand.equalsIgnoreCase("bye")) {
                    isExit = true;
                    ui.showGoodbye();
                } else {
                    Parser.parse(fullCommand, tasks, ui, storage);
                }
            } catch (BopesException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * The main method to run the Bopes application.
     * 
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        new Bopes("data/tasks.txt").run();
    }
}
