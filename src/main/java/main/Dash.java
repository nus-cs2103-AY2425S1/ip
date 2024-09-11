package main;

import command.Command;
import exception.DashException;

/**
 * Represents the main application for managing tasks using commands.
 * It handles the interaction between the user, task storage, and command execution.
 */
public class Dash {
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

    /**
     * Constructs a Dash application with the specified file path for task storage.
     *
     * @param filePath The path to the file where tasks are stored and loaded from.
     */
    public Dash(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

    /**
     * Runs the Dash application, continuously processing user inputs and executing commands
     * until the exit condition is met. Handles exceptions and ensures the UI is updated accordingly.
     */
    public String run(String input) {
        boolean isExit = false;
        String message = "";
        try {
            Command command = Parser.parse(input);
            message = command.execute(tasks, storage);
            isExit = command.isExit();
        } catch (DashException e) {
            return Ui.showError(e.getMessage());
        }
        if (isExit) {
            return Ui.displayGoodbye();
        }
        return message;
    }
}
