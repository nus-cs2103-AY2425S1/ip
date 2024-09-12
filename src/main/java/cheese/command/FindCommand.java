package cheese.command;

import cheese.Storage;
import cheese.TaskList;
import cheese.Ui;

/**
 * Command to search through tasks.
 */
public class FindCommand extends Command {
    private final String query;

    /**
     * Creates a FindCommand, requires query to find task.
     *
     * @param query string to query against.
     */
    public FindCommand(String query) {
        this.query = query;
    }

    /**
     * Returns string of tasks with name that is a substring of the keyword.
     *
     * @param tasks list of tasks.
     * @param ui format response.
     * @param storage store data.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList matchedTasks = tasks.search(query);
        return ui.say(matchedTasks);
    }
}
