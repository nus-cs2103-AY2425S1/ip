package myapp.command;

import myapp.core.BingBongUI;
import myapp.core.Storage;
import myapp.task.TaskList;
import myapp.task.Task;

public class UnMarkCommand extends Command {

    int index;
    public UnMarkCommand(int i) {
        super();
        this.index = i;
    }

    @Override
    public void execute(TaskList tasks, BingBongUI ui, Storage storage)
            throws IndexOutOfBoundsException {
        if (index < 0 || index >= tasks.size()) {
            throw new IndexOutOfBoundsException();
        }
        Task task = tasks.get(index);
        task.markAsNotDone();
        saveTasks(tasks, ui, storage);
        ui.showResponse("OK, I've marked this task as not done yet:\n" + task);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
