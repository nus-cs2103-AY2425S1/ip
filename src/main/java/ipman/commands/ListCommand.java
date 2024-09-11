package ipman.commands;

import ipman.models.Task;
import ipman.models.TaskList;

import java.util.StringJoiner;

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
        StringJoiner sj = new StringJoiner("\n");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            sj.add(String.format("%d. %s", i + 1, task));
        }
        context.ui().showMessage(sj.toString());
    }
}
