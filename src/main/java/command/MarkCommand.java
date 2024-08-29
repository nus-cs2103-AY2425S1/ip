package command;

import exception.DudeException;
import storage.Storage;
import storage.TaskList;
import ui.Ui;

/**
 * Represents a command to mark a task as done.
 */
public class MarkCommand extends Command {
    private int index;

    /**
     * Creates a command.MarkCommand with the given task number.
     *
     * @param index The task number to be marked.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Marks the task in the task list and updates the storage file.
     *
     * @param tasks The task list in which the task is to be marked.
     * @param ui The user interface to interact with the user.
     * @param storage The storage file to be updated.
     * @throws DudeException If there is an error marking the task.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DudeException {
        tasks.markDone(index);
        storage.saveTasks(tasks);
        ui.showMarked(tasks.getTask(index));
    }

    /**
     * Returns false because this is not an exit command.
     *
     * @return false.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
