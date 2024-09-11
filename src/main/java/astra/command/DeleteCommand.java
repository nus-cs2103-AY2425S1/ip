package astra.command;

import astra.AstraException;
import astra.Storage;
import astra.TaskList;
import astra.Ui;
import astra.task.Task;

/**
 * Represents a command to delete a task.
 */
public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AstraException {
        Task t = tasks.delete(index);
        storage.save(tasks);
        String msg = " Noted. I've removed this task: \n  " + t + "\n";
        ui.display(msg);
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws AstraException {
        Task t = tasks.delete(index);
        storage.save(tasks);
        return " Noted. I've removed this task: \n  " + t + "\n";
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
