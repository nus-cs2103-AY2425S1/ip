package skibidi.command;

import java.io.IOException;

import skibidi.exceptions.SkibidiException;
import skibidi.task.Task;
import skibidi.utils.Storage;
import skibidi.utils.TaskList;

/**
 * Represents a delete command.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";
    private final int index;

    /**
     * Creates a delete command.
     *
     * @param input Array of input strings
     * @throws SkibidiException If the input is invalid
     */
    public DeleteCommand(String[] input) throws SkibidiException {
        super(COMMAND_WORD);
        validateInput(input);
        try {
            this.index = Integer.parseInt(input[1]) - 1;
        } catch (NumberFormatException e) {
            throw new SkibidiException("Error: delete index must be a number. "
                    + "\nTo delete a task, use the format: delete <index>");
        }
    }
    /**
     * Validates the input.
     *
     * @param input Array of input strings
     * @throws SkibidiException If the input is invalid
     */
    public void validateInput(String[] input) throws SkibidiException {
        if (input.length < 2) {
            throw new SkibidiException("Error: delete index must not be empty. "
                    + "\nTo delete a task, use the format: delete <index>");
        }
    }

    @Override
    public String execute(Storage storage, TaskList taskList) throws IOException {
        Task task = taskList.deleteTask(index);
        storage.save(taskList);
        return "Gyatt. I've yeeted this goon quest:\n" + task + "\nNow you have " + taskList.size()
                + " quests in the list.";
    }
}
