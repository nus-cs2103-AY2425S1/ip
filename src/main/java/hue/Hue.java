package hue;

import hue.command.*;
import hue.storage.Storage;
import hue.task.TaskList;
import hue.UI.UI;
import hue.parser.Parser;
import java.io.FileNotFoundException;
import java.io.IOException;


/**
 * The main class of the application that handles the execution of commands and manages the overall workflow.
 * The class initializes the user interface, storage, and task list, and runs the main loop of the application.
 */
public class Hue {

    private static Storage storage;
    private static TaskList tasks;
    private final UI ui;
    /**
     * Creates a {@code Hue} object with the specified file path for task storage.
     * Initializes the user interface and attempts to load tasks from the specified file.
     *
     * @param filePath The path to the file where tasks are stored.
     */
    public Hue(String filePath) {
        this.ui = new UI();
        storage = new Storage(filePath);

        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (FileNotFoundException e) {
            ui.showError("File not found. Creating new file.");
        } catch (HueException e) {
            ui.showError("Error loading tasks: " + e.getMessage());
            tasks = new TaskList();
        }
    }
    /**
     * Runs the main loop of the application. Reads user commands, parses them, and executes them.
     * Continues until an exit command is issued.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command command = Parser.parse(fullCommand);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (HueException | IOException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main (String[] args) {
        new Hue("data/Hue.txt").run();
    }


}
