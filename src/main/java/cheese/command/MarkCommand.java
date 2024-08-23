package cheese.command;

import cheese.CheeseException;
import cheese.Storage;
import cheese.TaskList;
import cheese.Ui;
import cheese.task.Task;

/**
 * Command to mark a task as done
 */
public class MarkCommand extends UpdateCommand{
    private final boolean done;
    private final int idx;
    public MarkCommand(int idx, boolean done) {
        super(idx, false);
        this.idx = idx;
        this.done = done;
    }

    /**
     * Uses the UpdateCommand execute() to save
     * @param tasks list of tasks
     * @param ui format response
     * @param storage store data
     * @throws CheeseException
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws CheeseException {
        Task t = tasks.get(idx);
        t.setDone(done);
        super.execute(tasks, ui, storage);
    }
}
