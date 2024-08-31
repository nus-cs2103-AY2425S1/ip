package duck;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import duck.commands.Command;
import duck.data.TaskList;
import duck.data.exception.DuckException;
import duck.parser.Parser;
import duck.storage.Storage;
import duck.ui.Ui;

/**
 * The Duck class serves as the main entry point for the Duck application.
 * It handles the initialization of the application, manages the task list,
 * and processes user commands.
 */
public class Duck {
    /** The default file path where the task list is stored. */
    private static final String FILE_PATH = "data/duck.txt";

    /** Manages the list of tasks. */
    private final TaskList tasks;

    /** Handles the loading and saving of tasks to a file. */
    private Storage storage;

    /** Handles the user interface operations such as displaying messages. */
    private final Ui ui;


    /**
     * Initializes the Duck application with the default file path for storing tasks.
     */
    public Duck() {
        this(FILE_PATH);
    }


    /**
     * Initializes the Duck application by setting up the user interface,
     * loading tasks from the storage, and displaying startup messages.
     *
     * @param filePath The path to the file where tasks are stored.
     */
    public Duck(String filePath) {
        this.ui = new Ui();
        tasks = new TaskList();
        try {
            ui.showStartUpMessage();
            storage = new Storage(filePath);
            storage.loadTasks(tasks);
            ui.showStartUpCompleteMessage();
        } catch (DuckException e) {
            ui.displayDukeExceptionMessage(e);
        }
    }

    /**
     * Runs the Duck application, which continuously reads and processes user commands
     * until the exit command is given.
     */
    public void run() {
        ui.sayHi();
        boolean isExit = false;
        while (!isExit) {
            try {
                String message = ui.readCommand();
                Command command = Parser.parse(message);
                command.execute(tasks, storage, ui);
                isExit = command.isExit();
            } catch (DuckException e) {
                ui.displayDukeExceptionMessage(e);
            }
        }
    }

    public String getResponse(String message) {
        // Create a ByteArrayOutputStream to capture the output
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream originalOut = System.out; // Save the original System.out
        System.setOut(new PrintStream(baos)); // Redirect System.out to the ByteArrayOutputStream

        try {
            // Parse the message and execute the command
            Command command = Parser.parse(message);
            command.execute(tasks, storage, ui);
        } catch (DuckException e) {
            ui.displayDukeExceptionMessage(e);
        } finally {
            // Restore the original System.out
            System.setOut(originalOut);
        }

        // Convert the captured output to a string
        return baos.toString();
    }

    /**
     * The main method serves as the entry point of the Duck application.
     * It creates a new Duck instance and starts the application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        new Duck(FILE_PATH);
        // new Duck(FILE_PATH).run();
    }
}
