package seedu.avo.commands;

import seedu.avo.exceptions.AvoException;
import seedu.avo.tasks.TaskManager;
/**
 * Represents the command to delete a task
 */
public class DeleteCommand extends Command {
    private static DeleteCommand instance;
    private static final int INPUT_SIZE = 2;
    private final TaskManager manager;
    private DeleteCommand(TaskManager manager) {
        this.manager = manager;
    }
    /**
     * Returns a singleton instance of DeleteCommand
     * @param manager A TaskManager to control task specific jobs
     * @return A single instance of DeleteCommand
     */
    public static DeleteCommand of(TaskManager manager) {
        if (instance == null) {
            instance = new DeleteCommand(manager);
        }
        return instance;
    }
    @Override
    public CommandResult execute(String userInput) throws AvoException {
        String[] inputs = userInput.split(" ");
        if (inputs.length < INPUT_SIZE) {
            throw new AvoException("OOPS!!! The task number cannot be empty.");
        }
        String message = manager.deleteTask(Integer.parseInt(inputs[1]) - 1);
        return new CommandResult(message, false);
    }
}
