package handsome;

import java.io.IOException;

import handsome.command.Command;
import handsome.exception.HandsomeException;

/**
 * Represents the main chatBot application named Handsome, which operates
 * based on Object-Oriented Programming principles. The Handsome class is
 * responsible for managing user interactions, loading tasks, and executing
 * commands.
 */
public class Handsome {
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;
    private String commandType = "";

    /**
     * Constructs a new Handsome chatBot with initialized storage, task list,
     * and user interface.
     * The chatBot loads existing tasks from storage upon initialization.
     */
    public Handsome() {
        ui = new Ui();
        storage = new Storage();
        tasks = new TaskList(storage.load());
    }

    /**
     * Runs the main loop of the Handsome chatBot, greeting the user and
     * processing input commands until the user exits.
     * The method handles user input, command execution, and error handling
     * throughout the interaction.
     */
    public String run(String input) {
        try {
            Command command = Parser.parse(input);
            String output = command.execute(tasks, storage, ui);
            commandType = command.getClass().getSimpleName();
            return output;
        } catch (HandsomeException error) {
            return ui.showError(error.getMessage());
        } catch (IOException ioException) {
            return ui.showError("IO Error: " + ioException.getMessage());
        }
    }

    public String getCommandType() {
        return commandType;
    }
}
