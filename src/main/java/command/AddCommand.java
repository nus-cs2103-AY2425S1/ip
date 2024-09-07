package command;

import task.Task;
import task.TaskList;

/**
 * Command which adds a task to the task list when executed by Him.
 *
 * @author IsaacPangTH
 */
public class AddCommand extends Command {

    private Task task;

    /**
     * Constructor for<code>AddCommand</code>.
     *
     * @param task Task to be added.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public String execute(TaskList list) {
        list.add(this.task);
        return him.Ui.sayAdded(this.task);
    }
}
