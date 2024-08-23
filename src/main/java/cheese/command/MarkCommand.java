package cheese.command;

import cheese.CheeseException;
import cheese.Storage;
import cheese.TaskList;
import cheese.Ui;
import cheese.task.Task;

public class MarkCommand extends UpdateCommand{
    private final boolean done;
    private final int idx;
    public MarkCommand(int idx, boolean done) {
        super(idx, false);
        this.idx = idx;
        this.done = done;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws CheeseException {
        Task t = tasks.get(idx);
        t.setDone(done);
        super.execute(tasks, ui, storage);
    }
}
