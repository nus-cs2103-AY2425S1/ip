package optimus;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * The main class for the Optimus task management application.
 * The Optimus class initializes the necessary components and runs the application.
 */
public class Optimus {

    private static final String FILE_PATH = "./data/optimus.txt";  // Default file path for storing tasks.
    private Storage storage;  // Handles loading and saving tasks.
    private TaskList tasks;  // Manages the list of tasks.
    private Ui ui;  // Handles user interaction.

    /**
     * Constructs an object with the specified file path for storing tasks.
     */
    public Optimus(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadFile());
        } catch (FileNotFoundException | OptimusException e) {
            ui.printError(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Starts the main loop of the Optimus application, reading and processing user commands.
     */
    public void run() {
        ui.showWelcome();
        while (true) {
            String command = ui.readCommand();
            try {
                Task task = Parser.parseCommand(command, tasks, ui, storage);
                if (task != null) {
                    tasks.addTask(task);
                    ui.TaskAdded(task, tasks.sizeOfRecord());
                }
            } catch (OptimusException | IOException e) {
                ui.printError(e.getMessage());
            }
        }
    }

    /**
     * The entry point for the Optimus application.
     */
    public static void main(String[] args) {
        new Optimus(FILE_PATH).run();
    }
}
