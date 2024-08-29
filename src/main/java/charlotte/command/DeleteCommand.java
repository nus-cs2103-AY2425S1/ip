package charlotte.command;

import charlotte.command.Command;
import charlotte.exception.CharlotteException;
import charlotte.storage.Storage;
import charlotte.task.Task;
import charlotte.task.TaskList;
import charlotte.ui.Ui;

public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws CharlotteException {
        if (index < 1 || index > tasks.getSize()) {
            throw new CharlotteException("Task number is invalid. Please try again");
        }
        Task deletedTask = tasks.deleteTask(index - 1);
        ui.showMessage("Noted. I've removed this task:\n " + deletedTask
                + "\n Now you have " + tasks.getSize() + " tasks in the list.");
        storage.saveTasks(tasks);
    }
}
