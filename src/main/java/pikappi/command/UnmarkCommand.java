package pikappi.command;

import pikappi.Storage;
import pikappi.TaskList;
import pikappi.Ui;

public class UnmarkCommand extends Command {
    private int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.unmarkTask(index);
        storage.save(tasks);
    }
}
