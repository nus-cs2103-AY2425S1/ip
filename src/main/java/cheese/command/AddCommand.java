package cheese.command;

import cheese.CheeseException;
import cheese.Storage;
import cheese.TaskList;
import cheese.Ui;
import cheese.task.Task;

/**
 * Command to add tasks to list
 */
public class AddCommand extends Command {
    private final Task task;

    /**
     * Only constructor for add command
     * @param task task to add
     */
    public AddCommand(Task task) {
        super();
        this.task = task;
    }

    /**
     * Function to call to add the task
     * @param tasks list of tasks
     * @param ui format response
     * @param storage store data
     * @throws CheeseException if Storage fails
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws CheeseException {
        storage.add(task);
        tasks.add(task);
        ui.say(task, tasks, false);
    }
}
