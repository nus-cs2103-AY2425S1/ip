package luna.command;

import java.util.ArrayList;

import luna.Storage;
import luna.TaskList;
import luna.task.Task;

/**
 * Represents a command to find tasks from current list of tasks.
 */
public class FindCommand extends Command {
    private final String query;

    /**
     * Creates command to search list of tasks.
     *
     * @param query Description of task to search for.
     */
    public FindCommand(String query) {
        this.query = query;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        String matched = tasks.find(query);

        if (matched.isEmpty()) {
            return "No task with matching description";
        }

        return "Here are the tasks with the matching description:\n" + matched;
    }
}
