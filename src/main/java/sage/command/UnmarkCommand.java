package sage.command;

import sage.exception.SageException;
import sage.task.TaskList;
import sage.task.Task;
import sage.ui.Ui;
import sage.storage.Storage;

public class UnmarkCommand extends Command {
    private int index;

    public UnmarkCommand(int index) {
        this.index = index - 1;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SageException {
        try {
            Task task = tasks.get(index);
            task.markAsNotDone();
            ui.showMessage("OK, please remember to complete this task later:\n" + task);
            storage.saveTasks(tasks);
        } catch (IndexOutOfBoundsException e) {
            throw new SageException("Oh no! This task number is invalid :(");
        }
    }
}
