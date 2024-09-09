package cheese.command;

import cheese.Storage;
import cheese.TaskList;
import cheese.Ui;
import cheese.exception.CheeseException;
import cheese.task.Task;

/**
 * Command to mark a Task as done
 */
public class MarkCommand extends UpdateCommand {
    private final boolean setDone;
    private final int idx;

    /**
     * Creates a MarkCommand, needs idx of task and boolean to indicate if done
     * @param idx int
     * @param setDone bool
     */
    public MarkCommand(int idx, boolean setDone) {
        super(idx, false);
        this.idx = idx;
        this.setDone = setDone;
    }

    /**
     * Sets tasks to setDone, save and returns Ui response
     * @param tasks list of tasks
     * @param ui format response
     * @param storage store data
     * @throws CheeseException if UpdateCommand fails
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws CheeseException {
        Task t = tasks.get(idx);
        t.setDone(setDone);
        return super.execute(tasks, ui, storage);
    }
}
