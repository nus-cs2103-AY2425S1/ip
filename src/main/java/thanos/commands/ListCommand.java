package thanos.commands;

import static thanos.utility.ResponseFormatter.generateTaskListResponse;

import java.util.ArrayList;

import thanos.exceptions.InvalidCommandException;
import thanos.tasks.Task;
import thanos.tasks.TaskList;

/**
 * Represents a command to list all tasks in the {@code TaskList}.
 */
public class ListCommand extends Command {
    /**
     * Constructs a {@code ListCommand} with the given argument.
     *
     * @param argument the argument associated with this command. This argument is not used for this command.
     */
    public ListCommand(String argument) {
        super(argument);
    }

    /**
     * Executes the command to retrieve and display the list of all tasks.
     * <p>
     * This method retrieves the current list of tasks from the {@code TaskList} and returns a formatted string
     * that lists the tasks with an appropriate header.
     * </p>
     *
     * @param taskList The {@code TaskList} containing the tasks to be retrieved and displayed.
     * @return A formatted string listing all tasks along with a header.
     * @throws InvalidCommandException If there is an issue with retrieving or formatting the tasks.
     */
    @Override
    public String execute(TaskList taskList) throws InvalidCommandException {
        ArrayList<Task> result = taskList.getTaskList();
        return generateTaskListResponse(result, "Here are the tasks in your list:");
    }
}
