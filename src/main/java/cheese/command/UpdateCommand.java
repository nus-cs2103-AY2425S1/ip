package cheese.command;

import cheese.CheeseException;
import cheese.Storage;
import cheese.TaskList;
import cheese.Ui;
import cheese.task.Task;

public class UpdateCommand extends Command{
    private final boolean isDelete;
    private final int idx;

    public UpdateCommand(int idx, boolean isDelete) {
        super();
        this.idx = idx;
        this.isDelete = isDelete;
    }

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
