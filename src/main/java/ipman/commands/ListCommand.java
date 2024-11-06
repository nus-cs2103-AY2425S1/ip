package ipman.commands;

import java.util.StringJoiner;

import ipman.models.Task;
import ipman.models.TaskList;

/**
 * Displays all the <code>Task</code>s from the <code>Context</code>'s
 * <code>TaskList</code>.
 *
 * @see Task
 * @see Context
 * @see TaskList
 */
public class ListCommand implements Command {
    @Override
    public void execute(Context context) {
        TaskList tasks = context.tasks();
        if (tasks.size() == 0) {
            context.ui().showMessage("There are no tasks to list.");
            return;
        }

        StringJoiner sj = new StringJoiner("\n");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            sj.add(String.format("%d. %s", i + 1, task));
        }
        context.ui().showMessage(sj.toString());
    }
}
