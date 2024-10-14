package seedu.avo.commands;

import seedu.avo.exceptions.AvoException;
import seedu.avo.tasks.TaskManager;
/**
 * Represents the command to mark a task as completed
 */
public class MarkCommand extends Command {
    private static MarkCommand instance;
    private static final int INPUT_SIZE = 2;
    private final TaskManager manager;
    private MarkCommand(TaskManager manager) {
        this.manager = manager;
    }
    /**
     * Returns a singleton instance of MarkCommand
     * @param manager A TaskManager to control task specific jobs
     * @return A single instance of MarkCommand
     */
    public static MarkCommand of(TaskManager manager) {
        if (instance == null) {
            instance = new MarkCommand(manager);
        }
        return instance;
    }
    @Override
    public CommandResult execute(String userInput) throws AvoException {
        String[] inputs = userInput.split(" ");
        if (inputs.length < INPUT_SIZE) {
            throw new AvoException("OOPS!!! The task number cannot be empty.");
        }
        String message = manager.completeTask(Integer.parseInt(inputs[1]) - 1);
        return new CommandResult(message, false);
    }
}
