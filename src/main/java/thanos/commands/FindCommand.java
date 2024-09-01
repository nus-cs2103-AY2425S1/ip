package thanos.commands;

import static thanos.utility.ResponseFormatter.generateTaskListResponse;

import java.util.ArrayList;

import thanos.exceptions.InvalidCommandException;
import thanos.tasks.Task;
import thanos.tasks.TaskList;

/**
 * Represents a command to find tasks in the {@code TaskList} that match a given keyword.
 */
public class FindCommand extends Command {
    /**
     * Constructs a {@code FindCommand} with the specified keyword argument.
     *
     * @param argument The keyword used to search for matching tasks.
     */
    public FindCommand(String argument) {
        super(argument);
    }

    /**
     * Executes the find command by searching for tasks that contain the specified keyword.
     * <p>
     * This method parses the command argument to extract the keyword and searches the {@code TaskList}
     * for tasks that contain this keyword. It then returns a formatted response with the matching tasks.
     * If no keyword is provided, an {@code InvalidCommandException} is thrown.
     * </p>
     *
     * @param taskList The {@code TaskList} containing all tasks to be searched.
     * @return A formatted string listing the matching tasks along with a header.
     * @throws InvalidCommandException If no keyword is provided in the command argument.
     */
    @Override
    public String execute(TaskList taskList) throws InvalidCommandException {
        if (this.getArgument().isEmpty()) {
            throw new InvalidCommandException(
                    "No keyword provided. Please use the correct format: 'find [keyword]'"
            );
        }

        ArrayList<Task> result = taskList.find(this.getArgument());
        return generateTaskListResponse(result, "Here are the matching tasks in your list:");
    }
}
