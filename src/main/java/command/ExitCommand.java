package command;

import exception.InvalidCommandKukiShinobuException;
import storage.Storage;
import task.TaskList;

/**
 * Represents a command that signals the termination of the application.
 * The {@code ExitCommand} is used to indicate that the application should exit.
 * This command does not interact with the task list or storage.
 */
public class ExitCommand extends Command {

    /**
     * Constructs an {@code ExitCommand}.
     * This command should not receive any arguments.
     * If arguments are passed, it throws an {@code InvalidCommandKukiShinobuException}.
     *
     * @param arguments A string representing the user input after the "exit" keyword.
     *                  This must be an empty string, as the exit command does not accept any arguments.
     * @throws InvalidCommandKukiShinobuException If any arguments are provided.
     */
    public ExitCommand(String arguments) throws InvalidCommandKukiShinobuException {
        if (!arguments.isEmpty()) {
            throw new InvalidCommandKukiShinobuException("bye command does not accept any arguments!");
        }
    }

    /**
     * Executes the exit command.
     * This method effectively does nothing other than returning an empty string, as
     * the exit command's purpose is to signal termination of the application.
     *
     * @param taskList The current task list, which is not used in this command.
     * @param storage  The storage object, which is not used in this command.
     * @return An empty string, as no additional action is required by the command.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) {
        return "";
    }

    /**
     * Indicates whether this command is an exit command.
     *
     * @return {@code true}, as this command signifies that the application should terminate.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
