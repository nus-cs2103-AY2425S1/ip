package skibidi.command;

import java.io.IOException;

import skibidi.exceptions.SkibidiException;
import skibidi.utils.Storage;
import skibidi.utils.TaskList;


/**
 * Represents a command to mark a task as done.
 */
public class MarkCommand extends Command {
    public static final String COMMAND_WORD = "mark";
    private int index;

    /**
     * Creates a mark command.
     *
     * @param input Array of input strings
     * @throws SkibidiException If the input is invalid
     */
    public MarkCommand(String[] input) throws SkibidiException {
        super(COMMAND_WORD);
        validateInput(input);
        this.index = Integer.parseInt(input[1]) - 1;
    }
    /**
     * Validates the input.
     *
     * @param input Array of input strings
     * @throws SkibidiException If the input is invalid
     */
    public void validateInput(String[] input) throws SkibidiException {
        if (input.length == 1) {
            throw new SkibidiException("Error: mark index must not be empty. "
                    + "\nTo mark a task as done, use the format: mark <index>");
        }
    }

    @Override
    public String execute(Storage storage, TaskList taskList) throws IOException {
        if (index < 0 || index >= taskList.size()) {
            return "Error: Invalid index. Please enter a valid index.";
        }
        taskList.markTaskAsDone(index);
        storage.save(taskList);
        return "Skibidi! This task is gooned:\n" + taskList.getTask(index).toString();
    }
}
