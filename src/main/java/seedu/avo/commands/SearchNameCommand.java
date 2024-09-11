package seedu.avo.commands;

import seedu.avo.exceptions.AvoException;
import seedu.avo.tasks.TaskManager;

/**
 * Represents the command to search tasks by name
 */
public class SearchNameCommand extends Command {
    private static final int INPUT_SIZE = 2;
    private final TaskManager manager;
    public SearchNameCommand(TaskManager manager) {
        this.manager = manager;
    }
    @Override
    public CommandResult execute(String userInput) throws AvoException {
        String[] inputs = userInput.split(" ", 2);
        if (inputs.length < INPUT_SIZE) {
            throw new AvoException("OOPS!!! The date cannot be empty.");
        }
        String message = manager.getTasksByName(inputs[1]);
        return new CommandResult(message);
    }
}
