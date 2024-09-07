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


    private final String startUpMessage;


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
        ui = new Ui();
        tasks = new TaskList();
        startUpMessage = startUpAndLoadStorage(filePath);

    }

    /**
     * Runs the Duck application, which continuously reads and processes user commands
     * until the exit command is given.
     */
    public void run() {
        assert ui != null;

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

    private String startUpAndLoadStorage( String filePath) {
        assert ui != null;
        return captureOutput(() -> {
            try {
                ui.showStartUpMessage();
                storage = new Storage(filePath);
                storage.loadTasks(tasks);
                ui.showStartUpCompleteMessage();
            } catch (DuckException e) {
                ui.displayDukeExceptionMessage(e);
            }
        });
    }

    /**
     * Returns the startup message generated by the Duck application.
     *
     * @return The startup message generated by the Duck application.
     */
    public String getStartUpMessage() {
        return startUpMessage;
    }

    /**
     * Returns the response generated by the Duck application for the given user input.
     *
     * @return The response generated by the Duck application.
     */
    public String getResponse(String message) {
        // Create a ByteArrayOutputStream to capture the output
        return captureOutput(() -> {
            try {
                Command command = Parser.parse(message);
                command.execute(tasks, storage, ui);
            } catch (DuckException e) {
                ui.displayDukeExceptionMessage(e);
            }
        });
    }

    private String captureOutput(Runnable runnable) {
        // Create a ByteArrayOutputStream to capture the output
        ByteArrayOutputStream baos = new ByteArrayOutputStream();


        PrintStream originalOut = System.out; // Save the original System.out
        System.setOut(new PrintStream(baos)); // Redirect System.out to the ByteArrayOutputStream

        assert runnable != null : "Command to be executed is Null!";
        runnable.run();

        // Ensure all output is flushed
        System.out.flush();

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
        new Duck(FILE_PATH).run();
    }
}
