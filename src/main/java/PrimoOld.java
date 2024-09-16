import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import commands.Command;
import exception.PrimoException;
import parser.Parser;
import storage.Storage;
import tasks.TaskList;
import ui.Ui;

/**
 * The Primo class is the entry point for the task management application.
 * It initializes the UI, storage, and task list, and runs the main application loop.
 */
public class PrimoOld {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a Primo instance with the specified file path.
     * Initializes the UI, storage, and task list.
     * If loading tasks from storage fails, it initializes an empty task list and shows an error message.
     *
     * @param filePathString The path to the file where tasks are stored.
     */
    public PrimoOld(String filePathString) throws IOException {
        this.ui = new Ui();
        this.storage = new Storage(filePathString);
        try {
            this.tasks = new TaskList(storage.load()); // Throws PrimoException and IOException
        } catch (PrimoException | IOException e) {
            Path directoryPath = Paths.get("./data");
            Path filePath = directoryPath.resolve("data.txt");
            Files.createDirectories(directoryPath);
            Files.createFile(filePath);
            ui.showLoadingError();
            tasks = new TaskList(); // Initializes an empty task list on error
            this.tasks = new TaskList();
        }
    }

    /**
     * Starts the main application loop.
     * Reads user commands, parses them, executes the commands, and shows appropriate UI messages.
     * The loop continues until an exit command is encountered.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand(); // Reads command from user
                ui.showLine(); // Shows a divider line
                Command c = Parser.parse(fullCommand); // Parses the command, may throw PrimoException
                c.execute(tasks, ui, storage); // Executes the command, may throw PrimoException
                isExit = c.isExit(); // Checks if the command indicates exit
            } catch (PrimoException e) {
                ui.showError(e.getMessage()); // Displays error message if an exception occurs
            } finally {
                ui.showLine(); // Shows a divider line after each command execution
            }
        }
    }

    /**
     * The main method to start the application.
     * Creates an instance of Primo with the specified file path and starts the application loop.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) throws IOException {
        new PrimoOld("data/data.txt").run(); // Runs the application with the specified file path
    }
}
