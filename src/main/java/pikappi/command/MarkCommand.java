package pikappi.command;

import pikappi.Storage;
import pikappi.TaskList;
import pikappi.Ui;

public class MarkCommand extends Command {
    private int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.markTask(index);
        storage.save(tasks);
    }
}
