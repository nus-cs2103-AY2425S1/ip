package seedu.avo.commands;

import seedu.avo.exceptions.AvoException;
import seedu.avo.tasks.TaskManager;

/**
 * Represents the command to search tasks by name
 */
public class SearchNameCommand extends Command {
    private static SearchNameCommand instance;
    private static final int INPUT_SIZE = 2;
    private final TaskManager manager;
    private SearchNameCommand(TaskManager manager) {
        this.manager = manager;
    }
    /**
     * Returns a singleton instance of SearchNameCommand
     * @param manager A TaskManager to control task specific jobs
     * @return A single instance of SearchNameCommand
     */
    public static SearchNameCommand of(TaskManager manager) {
        if (instance == null) {
            instance = new SearchNameCommand(manager);
        }
        return instance;
    }
    @Override
    public CommandResult execute(String userInput) throws AvoException {
        String[] inputs = userInput.split(" ", 2);
        if (inputs.length < INPUT_SIZE) {
            throw new AvoException("OOPS!!! The date cannot be empty.");
        }
        String message = manager.getTasksByName(inputs[1]);
        return new CommandResult(message, false);
    }
}
