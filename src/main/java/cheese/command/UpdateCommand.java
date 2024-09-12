package cheese.command;

import cheese.Storage;
import cheese.TaskList;
import cheese.Ui;
import cheese.exception.CheeseException;
import cheese.task.Task;

/**
 * Command to update a Task
 */
public class UpdateCommand extends Command {
    private final boolean isDelete;
    private final int idx;

    /**
     * Creates an UpdateCommand. Require idx of task and whether delete or update.
     *
     * @param idx index of Task in TaskList.
     * @param isDelete bool.
     */
    public UpdateCommand(int idx, boolean isDelete) {
        super();
        this.idx = idx;
        this.isDelete = isDelete;
    }

    /**
     * Deletes/updates a Task from Storage and TaskList, returns response from Ui.
     *
     * @param tasks list of tasks.
     * @param ui format response.
     * @param storage store data.
     * @throws CheeseException if storage fails.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws CheeseException {
        Task t = tasks.get(idx);
        storage.updateTask(idx, tasks, isDelete);
        if (isDelete) {
            t = tasks.remove(idx);
        }
        return ui.say(t, tasks, isDelete);
    }
}
