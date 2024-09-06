package bob.Command;

import bob.Exception.BobException;
import bob.Storage.Storage;
import bob.Tasks.Task;
import bob.Ui.Ui;

import java.util.ArrayList;

public class DeleteCommand extends Command {

    private final int taskIndexDelete;

    public DeleteCommand(int taskIndexDelete) {
        this.taskIndexDelete = taskIndexDelete;
    }

    @Override
    public String execute(ArrayList<Task> tasks, Storage storage, Ui ui) throws BobException {
        if (taskIndexDelete < tasks.size() && taskIndexDelete >= 0) {
            Task removedTask = tasks.remove(taskIndexDelete);
            storage.save(tasks);
            return ui.showRemovedTask(removedTask, tasks.size());
        } else {
            throw new BobException("Invalid index :(");
        }
    }
}
