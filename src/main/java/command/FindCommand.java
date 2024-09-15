package command;

import exception.KukiShinobuException;
import storage.Storage;
import task.Task;
import task.TaskList;

import java.util.ArrayList;

/**
 * Represents a command to find tasks that match a given keyword.
 * <p>
 * The {@code FindCommand} searches for tasks in a {@code TaskList} that contain the specified keyword.
 * </p>
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Constructs a {@code FindCommand} with the specified keyword.
     *
     * @param arguments The keyword to search for in the task descriptions.
     * @throws KukiShinobuException If no keyword is provided.
     */
    public FindCommand(String arguments) throws KukiShinobuException {
        if (arguments.isEmpty()) {
            throw new KukiShinobuException("No keyword is provided!");
        }

        this.keyword = arguments;
    }

    /**
     * Executes the find command to search for tasks containing the keyword.
     * <p>
     * This method retrieves tasks from the {@code TaskList} that match the keyword and formats them
     * into a response string.
     * </p>
     *
     * @param taskList The {@code TaskList} from which to search for tasks.
     * @param storage  The {@code Storage} used to persist tasks (not used in this method but required by the method signature).
     * @return A {@code String} containing the result of the search, including matching tasks or a message if no tasks are found.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) {
        StringBuilder response = new StringBuilder();

        //TODO: Perhaps update findMatchingTasks to return a TaskList instead to we can reuse the toString method
        ArrayList<Task> matchingTasks = taskList.findMatchingTasks(this.keyword);

        if (matchingTasks.isEmpty()) {
            response.append("There are no tasks that contains the keyword!");
        } else {
            response.append("Here are the matching tasks in your list:");
            response.append(System.lineSeparator());
            for (int i = 0; i < matchingTasks.size(); i++) {
                response.append((i + 1)).append(".").append(matchingTasks.get(i)).append(System.lineSeparator());
            }
        }
        return response.toString();
    }
}
