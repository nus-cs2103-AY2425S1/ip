package seedu.avo.commands;

import seedu.avo.exceptions.AvoException;
import seedu.avo.tasks.TaskManager;
/**
 * Represents the command to mark a task as uncompleted
 */
public class UnmarkCommand extends Command {
    private static final int INPUT_SIZE = 2;
    private final TaskManager manager;
    public UnmarkCommand(TaskManager manager) {
        this.manager = manager;
    }
    @Override
    public CommandResult execute(String userInput) throws AvoException {
        String[] inputs = userInput.split(" ");
        if (inputs.length < INPUT_SIZE) {
            throw new AvoException("OOPS!!! The task number cannot be empty.");
        }
        String message = manager.unCompleteTask(Integer.parseInt(inputs[1]) - 1);
        return new CommandResult(message);
    }
}
