package astra.command;

import astra.AstraException;
import astra.Storage;
import astra.TaskList;
import astra.Ui;
import astra.task.Task;

/**
 * Represents a command to mark a task as done or not done.
 */
public class MarkCommand extends Command {
    private final int index;
    private final boolean isDone;

    /**
     * Constructor for MarkCommand.
     *
     * @param index The index of the task to mark.
     * @param isDone Whether to mark the task as done or not done.
     */
    public MarkCommand(int index, boolean isDone) {
        this.index = index;
        this.isDone = isDone;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AstraException {
        Task t = tasks.markAsDone(index, isDone);
        storage.save(tasks);
        String msg = isDone
                ? " Nice! I've marked this task as done: \n  " + t + "\n"
                : " OK, I've marked this task as not done yet: \n  " + t + "\n";
        ui.display(msg);
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws AstraException {
        Task t = tasks.markAsDone(index, isDone);
        storage.save(tasks);
        return isDone
                ? " Nice! I've marked this task as done: \n  " + t + "\n"
                : " OK, I've marked this task as not done yet: \n  " + t + "\n";
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
