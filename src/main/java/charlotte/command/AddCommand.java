package charlotte.command;

import charlotte.exception.CharlotteException;
import charlotte.storage.Storage;
import charlotte.task.Task;
import charlotte.task.TaskList;
import charlotte.ui.Ui;

public class AddCommand extends Command {
    private final Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    public Task getTask() {
        return this.task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws CharlotteException {
        tasks.addTask(task);
        ui.showMessage("Got it. I've added this task:\n  " + task
                + "\n Now you have " + tasks.getSize() + " tasks in the list.");
        storage.saveTasks(tasks);
    }
}
