package cheese.command;

import cheese.Storage;
import cheese.TaskList;
import cheese.Ui;
import cheese.exception.CheeseException;
import cheese.task.Task;

/**
 * Command to add tasks to list
 */
public class AddCommand extends Command {
    private final Task task;

    /**
     * Creates AddCommand and saves Task to add
     * @param task task to add
     */
    public AddCommand(Task task) {
        super();
        this.task = task;
    }

    /**
     * Adds task to Storage, TaskList and returns Ui response
     * @param tasks list of tasks
     * @param ui format response
     * @param storage store data
     * @throws CheeseException if Storage fails
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws CheeseException {
        storage.addTask(task);
        tasks.add(task);
        return ui.say(task, tasks, false);
    }
}
