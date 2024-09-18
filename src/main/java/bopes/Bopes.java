package bopes;

import java.util.ArrayList;
import java.io.File;

import bopes.exception.BopesException;
import bopes.task.TaskList;

/**
 * Main class for the Bopes application.
 */
public class Bopes {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a Bopes object and initializes the task list and UI.
     */
    public Bopes() {
        ui = new Ui();
        String homeDirectory = System.getProperty("user.home");
        String filePath = homeDirectory + File.separator + "Bopes" + File.separator + "tasks.txt";
        File bopesDir = new File(homeDirectory + File.separator + "Bopes");
        if (!bopesDir.exists()) {
            bopesDir.mkdir();
        }
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadTasks());
            ui.showTasks(tasks);
        } catch (BopesException e) {
            ui.showLoadingError();
            tasks = new TaskList(new ArrayList<>());
        }
    }

    /**
     * Checks if the user input is a valid command.
     *
     * @param input The user input.
     * @return true if the command is valid, false otherwise.
     */
    public boolean isValidCommand(String input) {
        return input.equals("hello") || input.equals("bye");
    }

    /**
     * Handles user input and returns the response from Bopes.
     *
     * @param input The user input.
     * @return The response from Bopes.
     */
    public String getResponse(String input) {
        if (isValidCommand(input)) {
            return "This is a valid command response!";
        } else {
            return "Error: Invalid command.";
        }
    }

    /**
     * Parses the user input using the tasks and storage.
     *
     * @param input The user input.
     * @return The result of the command execution.
     */
    public CommandResult parseInput(String input) {
        return Parser.parse(input, tasks, storage);
    }

    /**
     * The main method to launch the Bopes application.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        Main.main(args);
    }
}
