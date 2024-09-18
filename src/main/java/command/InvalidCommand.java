package command;

import exception.KukiShinobuException;
import storage.Storage;
import task.TaskList;

/**
 * Represents a command that indicates an error in command creation.
 * <p>
 * The {@code InvalidCommand} class is used to handle cases where the user input is invalid,
 * and provides an error message to be displayed to the user.
 * </p>
 */
public class InvalidCommand extends Command {

    private final String errorMessage;

    /**
     * Constructs an {@code InvalidCommand} with the specified error message.
     *
     * @param errorMessage The error message to be returned to the user.
     */
    public InvalidCommand(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String execute(TaskList taskList, Storage storage) {
        return errorMessage;
    }
}
