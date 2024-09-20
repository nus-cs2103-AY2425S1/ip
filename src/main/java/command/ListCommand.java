package command;

import exception.InvalidCommandKukiShinobuException;
import storage.Storage;
import task.TaskList;

/**
 * Represents a command that displays the current list of tasks.
 * The {@code ListCommand} prints the tasks stored in the {@link TaskList}
 * to the user interface.
 */
public class ListCommand extends Command {

    /**
     * Constructs a {@code ListCommand}.
     * This command does not accept any arguments.
     * If arguments are provided, an {@code InvalidCommandKukiShinobuException} is thrown.
     *
     * @param arguments A string representing the user input after the "list" keyword.
     *                  This must be an empty string, as the list command does not accept any arguments.
     * @throws InvalidCommandKukiShinobuException If any arguments are provided.
     */
    public ListCommand(String arguments) throws InvalidCommandKukiShinobuException {
        if (!arguments.isEmpty()) {
            throw new InvalidCommandKukiShinobuException("list command does not accept any arguments!");
        }
    }

    /**
     * Executes the list command.
     * This method returns the string representation of the current task list,
     * which will be displayed to the user. The storage object is not utilized in this command.
     *
     * @param taskList The current task list to be displayed.
     * @param storage  The storage object, which is not used in this command.
     * @return A string representation of the tasks in the task list.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) {
        return taskList.toString();
    }
}
