package sage.command;

import sage.exception.SageException;
import sage.ui.Ui;
import sage.storage.Storage;
import sage.task.Task;
import sage.task.TaskList;

public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index - 1;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SageException {
        try {
            Task task = tasks.removeTask(index);
            ui.showMessage("OK! I will remove this task:\n" + task +
                    "\nNow you have " + tasks.size() +
                    (tasks.size() > 1 ? " tasks" : " task") + " in your list");
            storage.saveTasks(tasks);
        } catch (IndexOutOfBoundsException e) {
            throw new SageException("Oh no! This task number is invalid :(");
        }
    }
}
