package cheese.command;

import cheese.CheeseException;
import cheese.Storage;
import cheese.TaskList;
import cheese.Ui;
import cheese.task.Task;

/**
 * Command to update a command
 */
public class UpdateCommand extends Command {
    private final boolean isDelete;
    private final int idx;

    /**
     * Constructor for UpdateCommand. Require idx of task and whether delete or update
     * @param idx int
     * @param isDelete bool
     */
    public UpdateCommand(int idx, boolean isDelete) {
        super();
        this.idx = idx;
        this.isDelete = isDelete;
    }

    /**
     * Currently only support deleting a task
     * @param tasks list of tasks
     * @param ui format response
     * @param storage store data
     * @throws CheeseException if storage fails
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws CheeseException {
        Task t = tasks.get(idx);
        storage.update(idx, tasks, isDelete);
        if (isDelete) {
            t = tasks.remove(idx);
        }
        ui.say(t, tasks);
    }
}
