package main;

import command.Command;
import exception.DashException;

/**
 * Represents the main application for managing tasks using commands.
 * It handles the interaction between the user, task storage, and command execution.
 */
public class Dash {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

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
    public void run() {
        Ui.displayGreeting();
        boolean isExit = false;
        while (!isExit) {
            try {
                String input = ui.nextInput();
                Command command = Parser.parse(input);
                command.execute(tasks, storage);
                isExit = command.isExit();
            } catch (DashException e) {
                Ui.showError(e.getMessage());
            } finally {
                Ui.insertLine();
            }
        }
        Ui.displayGoodbye();
    }

    /**
     * The entry point of the application. Initializes and runs the Dash application with a specified file path.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        new Dash("./data/dash.txt").run();
    }
}
