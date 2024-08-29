package Bob.Command;

import Bob.Exception.BobException;
import java.util.ArrayList;
import Bob.Tasks.Task;
import Bob.Storage.Storage;
import Bob.Ui.Ui;

public class DeleteCommand extends Command {
    private final int taskIndexDelete;

    public DeleteCommand(int taskIndexDelete) {
        this.taskIndexDelete = taskIndexDelete;
    }

    @Override
    public void execute(ArrayList<Task> tasks, Storage storage, Ui ui) throws BobException {
        if (taskIndexDelete < tasks.size() && taskIndexDelete >= 0) {
            Task removedTask = tasks.remove(taskIndexDelete);
            storage.save(tasks);
            ui.showRemovedTask(removedTask, tasks.size());
        } else {
            throw new BobException("Invalid index :(");
        }
    }
}
