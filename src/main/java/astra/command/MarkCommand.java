package astra.command;

import astra.AstraException;
import astra.Storage;
import astra.TaskList;
import astra.Ui;
import astra.task.Task;

public class MarkCommand extends Command {
    private final int index;
    private final boolean asDone;

    public MarkCommand(int index, boolean asDone) {
        this.index = index;
        this.asDone = asDone;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AstraException {
        Task t = tasks.markAsDone(index, asDone);
        storage.save(tasks);
        String msg = asDone
                ? " Nice! I've marked this task as done: \n  " + t + "\n"
                : " OK, I've marked this task as not done yet: \n  " + t + "\n";
        ui.display(msg);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
