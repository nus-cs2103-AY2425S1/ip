package tudee.command;

import tudee.task.TaskList;
import tudee.task.Task;
import tudee.ui.Ui;
import tudee.storage.Storage;

public class MarkCommand extends Command {
    private final int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.get(index - 1);
        task.markAsDone();
        ui.showMark(task);
        storage.save(tasks.get());
    }
}