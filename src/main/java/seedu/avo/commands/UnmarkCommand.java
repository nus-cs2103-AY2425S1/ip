package seedu.avo.commands;

import seedu.avo.exceptions.AvoException;
import seedu.avo.tasks.TaskManager;
/**
 * Represents the command to mark a task as uncompleted
 */
public class UnmarkCommand extends Command {
    private static UnmarkCommand instance;
    private static final int INPUT_SIZE = 2;
    private final TaskManager manager;
    private UnmarkCommand(TaskManager manager) {
        this.manager = manager;
    }
    /**
     * Returns a singleton instance of UnmarkCommand
     * @param manager A TaskManager to control task specific jobs
     * @return A single instance of UnmarkCommand
     */
    public static UnmarkCommand of(TaskManager manager) {
        if (instance == null) {
            instance = new UnmarkCommand(manager);
        }
        return instance;
    }
    @Override
    public CommandResult execute(String userInput) throws AvoException {
        String[] inputs = userInput.split(" ");
        if (inputs.length < INPUT_SIZE) {
            throw new AvoException("OOPS!!! The task number cannot be empty.");
        }
        String message = manager.unCompleteTask(Integer.parseInt(inputs[1]) - 1);
        return new CommandResult(message, false);
    }
}
