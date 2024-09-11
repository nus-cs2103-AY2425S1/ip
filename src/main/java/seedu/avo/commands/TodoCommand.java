package seedu.avo.commands;

import seedu.avo.exceptions.AvoException;
import seedu.avo.tasks.TaskManager;
import seedu.avo.tasks.ToDo;
/**
 * Represents the command to add a todo task
 */
public class TodoCommand extends Command {
    private static final int INPUT_SIZE = 2;
    private final TaskManager manager;
    public TodoCommand(TaskManager manager) {
        this.manager = manager;
    }
    @Override
    public CommandResult execute(String userInput) throws AvoException {
        String[] inputs = userInput.split("todo ");
        if (inputs.length < INPUT_SIZE) {
            throw new AvoException("OOPS!!! The description of a todo cannot be empty.");
        }
        String message = manager.addTask(new ToDo(inputs[1].trim()));
        return new CommandResult(message);
    }
}
