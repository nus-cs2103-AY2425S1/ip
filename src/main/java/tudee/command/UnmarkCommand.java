package tudee.command;

import tudee.task.TaskList;
import tudee.task.Task;
import tudee.ui.Ui;
import tudee.storage.Storage;

public class UnmarkCommand extends Command {
    private final int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage){
        Task task = tasks.get(index - 1);
        task.markAsNotDone();
        ui.showUnMark(task);
        storage.save(tasks.get());
    }
}