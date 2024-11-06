package ipman.commands;

import java.util.ArrayList;
import java.util.List;

import ipman.models.Task;
import ipman.models.TaskList;

/**
 * Finds a particular <code>Task</code> from the <code>Context</code>'s
 * <code>TaskList</code>.
 *
 * @see Task
 * @see Context
 * @see TaskList
 */
public class FindCommand implements Command {
    private final String query;

    /**
     * Creates a command which will search for the <code>Task</code> that
     * matches the query.
     *
     * @param query part of <code>Task</code> to try to find
     */
    public FindCommand(String query) {
        this.query = query;
    }

    @Override
    public void execute(Context context) {
        TaskList tasks = context.tasks();
        List<Task> matched = new ArrayList<>();

        // Search through
        for (Task task : tasks) {
            if (task.getName().contains(this.query)) {
                matched.add(task);
            }
        }

        // Print results
        if (matched.isEmpty()) {
            context.ui().showMessage("No results found.");
            return;
        }

        StringBuilder sb = new StringBuilder("Here's what I found:");
        for (int i = 0; i < matched.size(); i++) {
            Task task = matched.get(i);
            sb.append(String.format("\n%d. %s", i + 1, task));
        }
        context.ui().showMessage(sb.toString());
    }
}
