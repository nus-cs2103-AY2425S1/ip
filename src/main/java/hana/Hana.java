package hana;

import java.io.IOException;

import hana.task.TaskList;

/**
 * Entry point for the Hana application.
 * Manages the initialization and execution of the program.
 */
public class Hana {

    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;
    // To keep track of commands awaiting confirmation
    private MassCommand awaitingCommand = null;
    /**
     * Initializes the Hana application with the given file path.
     * Loads the task list from the specified file.
     *
     * @param filePath The file path where the tasks are stored.
     */
    public Hana(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException | HanaException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Processes a single user command and returns the response as a string.
     *
     * @param input The user's command input.
     * @return The response of Hana based on the user command.
     */
    public String getResponse(String input) {
        try {
            // Check if previous command needs confirmation (mass command)
            if (awaitingCommand != null) {
                processConfirmation(input); // Process the confirmation step
                return ui.getResponseMessage();
            }

            // Parse and execute a new command
            Command command = Parser.parse(input);
            command.execute(tasks, ui, storage);

            // Check if the command requires further input (e.g., awaiting confirmation)
            if (command instanceof MassCommand massCommand && massCommand.isAwaitingConfirmation) {
                awaitingCommand = massCommand; // Store the command awaiting confirmation
            }

            return ui.getResponseMessage(); // Return the response message
        } catch (HanaException | IOException e) {
            return e.getMessage();
        }
    }

    /**
     * Handles the confirmation of mass commands awaiting user input.
     *
     * @param input The user's confirmation input.
     */
    private void processConfirmation(String input) throws IOException {
        awaitingCommand.confirm(input, tasks, ui, storage); // Confirm based on the parsed input
        awaitingCommand = null; // Reset awaitingCommand once confirmation is done
    }


}
