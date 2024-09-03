package Optimus;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * The main class for the Optimus task management application.
 * The {@code Optimus} class initializes the necessary components and runs the application.
 */
public class Optimus {

    private static final String FILE_PATH = "./data/optimus.txt";  // Default file path for storing tasks.
    private Storage storage;  // Handles storage-related operations like loading and saving tasks.
    private TaskList tasks;  // Manages the list of tasks in the application.
    private Ui ui;  // Handles user interaction, including input and output.

    /**
     * Constructs an {@code Optimus} object with the specified file path for storing tasks.
     *
     * @param filePath the file path for storing tasks.
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
     * Initializes and runs the application.
     *
     * @param args command-line arguments (not used).
     */
    public static void main(String[] args) {
        new Optimus(FILE_PATH).run();
    }
}
