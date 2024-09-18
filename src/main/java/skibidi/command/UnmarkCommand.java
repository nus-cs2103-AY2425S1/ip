package skibidi.command;

import java.io.IOException;

import skibidi.exceptions.SkibidiException;
import skibidi.utils.Storage;
import skibidi.utils.TaskList;


/**
 * Represents a command to mark a task as not done.
 */
public class UnmarkCommand extends Command {
    public static final String COMMAND_WORD = "unmark";

    private int index;

    /**
     * Creates an unmark command.
     *
     * @param input Array of input strings
     * @throws SkibidiException If the input is invalid
     */
    public UnmarkCommand(String[] input) throws SkibidiException {
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
            throw new SkibidiException("Error: unmark index must not be empty. "
                    + "\nTo unmark a task as not done, use the format: unmark <index>");
        }
    }

    @Override
    public String execute(Storage storage, TaskList taskList) throws IOException {
        if (index < 0 || index > taskList.size()) {
            return "Error: Invalid index. Please enter a valid index.";
        }
        taskList.unmarkTaskAsDone(index);
        storage.save(taskList);
        return "Got it. Un-yapped this task as not done:\n" + taskList.getTask(index).toString();
    }
}
