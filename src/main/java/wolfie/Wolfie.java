package wolfie;

import java.io.IOException;

import wolfie.command.Command;
import wolfie.exception.WolfieException;
import wolfie.task.TaskList;
import wolfie.util.Parser;
import wolfie.util.Storage;
import wolfie.util.Ui;

/**
 * Wolfie is a chat bot that helps you manage your tasks.
 */
public class Wolfie {
    private final Storage storage; // Add this line
    private TaskList tasks;
    private final Ui ui;

    /**
     * Constructs a Wolfie object.
     *
     * @param filePath The file path to store the tasks.
     */
    public Wolfie(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath); // If the file does not exist, it will be created
        try {
            tasks = new TaskList(storage.load()); // Load existing tasks
        } catch (IOException e) {
            ui.showLoadingError(); // Show error message
            tasks = new TaskList(); // Start with an empty list
        }
    }

    /**
     * Runs the Wolfie bot.
     */
    public void run() {
        ui.showWelcome(); // Show welcome message
        boolean isExit = false; // Initialize exit status to false
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand(); // Read the command
                ui.showLine(); // Show the line divider
                Command c = Parser.parse(fullCommand); // Parse the command
                c.execute(tasks, ui, storage); // Execute the command
                isExit = c.isExit();
            } catch (WolfieException | IOException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Gets the response from Wolfie.
     * @param input The user input.
     * @return The response from Wolfie.
     */
    public String getResponse(String input) {
        assert input != null : "User input should not be null.";
        assert !input.isBlank() : "User input should not be blank.";
        try {
            Command command = Parser.parse(input);
            return command.execute(tasks, ui, storage);
        } catch (WolfieException | IOException e) {
            return e.getMessage();
        }
    }
    public static void main(String[] args) {
        new Wolfie("data/tasks.txt").run();
    }
}
