package Naega;

import Naega.Command.Command;
import Naega.Parser.Parser;
import Naega.Storage.Storage;
import Naega.Task.*;
import Naega.Ui.Ui;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The main class for the Naega application.
 * Handles initialization, command processing, and application flow.
 */
public class Naega {

    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;
    private boolean firstRun;

    /**
     * Creates a new Naega instance with the specified file path.
     * Initializes the user interface and storage. Loads tasks from the storage
     * if available, otherwise initializes a new TaskList.
     *
     * @param filePath the path to the file where tasks are stored
     */
    public Naega(String filePath) {
        ui = new Ui();
        assert filePath != null && !filePath.isEmpty() : "File path must not be null or empty";
        storage = new Storage(filePath);

        // First-run logic: If no saved file is found, it's the first run
        firstRun = !storage.fileExists();
        if (firstRun) {
            tasks = new TaskList();
            loadSampleData();
            storage.save(tasks.getTasks());
            firstRun = true;  // Mark this as the first run
        } else {
            try {
                tasks = new TaskList(storage.load());
                firstRun = false;
            } catch (NaegaException e) {
                ui.showLoadingError();
                tasks = new TaskList();
            }
        }
    }

    /**
     * Checks if this is the first time the app is being run.
     *
     * @return true if it's the first run, false otherwise
     */
    public boolean isFirstRun() {
        return firstRun;
    }

    /**
     * Returns the help message that is displayed on the first run.
     * Includes sample data and instructions.
     *
     * @return a help message containing sample tasks and usage instructions
     */
    public String getHelpMessage() {
        return """
                Sample Data Loaded:
                - todo: Finish reading the help guide
                - deadline: Submit assignment by 2024-09-25 2359
                - event: Meet with mentor from 2024-09-30 1400 to 2024-09-30 1500
                """;
    }

    /**
     * Loads sample tasks for the first run.
     */
    public void loadSampleData() {
        tasks.addTask(new Todo("Sample Todo"));
        tasks.addTask(new Deadline("Sample Deadline", LocalDateTime.parse("2024-09-25T23:59", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"))));
        tasks.addTask(new Event("Sample Event", LocalDateTime.parse("2024-09-25T10:00", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm")),
                LocalDateTime.parse("2024-09-25T12:00", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"))));

        // Save the tasks to storage after loading the sample data
        System.out.println("Saving sample data...");
        storage.save(tasks.getTasks());
    }

    /**
     * Starts the main loop of the application. Continuously reads commands from
     * the user, parses and executes them, and handles errors. The loop terminates
     * when an exit command is encountered.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                assert fullCommand != null && !fullCommand.trim().isEmpty() : "Command should not be null or empty";
                ui.showLine();
                Command command = Parser.parse(fullCommand);
                assert command != null : "Parsed command should not be null";
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (NaegaException e) {
                ui.showError(e.getMessage());
            } catch (IOException e) {
                throw new RuntimeException("Unexpected error occurred during IO operations.", e);
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Processes the input command and returns the appropriate response.
     * This method is called in the GUI to handle user input.
     *
     * @param input the user's input command
     * @return the response from Naega
     */
    public String getResponse(String input) {
        assert input != null && !input.isEmpty() : "Input should not be null or empty";
        try {
            Command command = Parser.parse(input);
            assert command != null : "Parsed command should not be null";
            return command.execute(tasks, ui, storage);
        } catch (NaegaException | IOException e) {
            return "Error: " + e.getMessage();
        }
    }

    /**
     * The entry point of the application. Creates a new Naega instance with
     * the specified file path and starts the application by calling the run method.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        new Naega("data/tasks.txt").run();
    }
}