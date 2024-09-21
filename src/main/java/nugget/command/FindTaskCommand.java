package nugget.command;

import java.util.ArrayList;

import nugget.Storage;
import nugget.Task;
import nugget.TaskList;
import nugget.Ui;
import nugget.exception.NuggetException;

/**
 * Represents a command to find tasks based on a search query in the task list.
 * The {@code FindTaskCommand} allows the user to search for tasks
 * whose description matches a given search query and display them.
 */
public class FindTaskCommand implements Command {
    private String searchQuery;

    /**
     * Constructs a {@code FindTaskCommand} with the specified search query.
     *
     * @param searchQuery The search query string used to find matching tasks.
     */
    public FindTaskCommand(String searchQuery) {
        this.searchQuery = searchQuery;
    }

    /**
     * Executes the find task command by searching for tasks that match the search query
     * and displaying the matching tasks using the {@code Ui}.
     *
     * @param tasks   The task list containing the tasks.
     * @param ui      The {@code Ui} object used to interact with the user.
     * @param storage The {@code Storage} object to handle file storage, unused in this command.
     * @throws NuggetException If there is an error during task searching.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws NuggetException {
        ArrayList<Task> matchingTasks = tasks.findMatchingTasks(searchQuery);
        ui.showFindResults(matchingTasks);
    }
}
