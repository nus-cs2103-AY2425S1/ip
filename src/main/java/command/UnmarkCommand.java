package command;

import command.Command;
import exception.DudeException;
import storage.Storage;
import storage.TaskList;
import ui.Ui;

/**
 * Represents a command to unmark a task as done.
 */
public class UnmarkCommand extends Command {
    private int index;

    /**
     * Creates a command.UnmarkCommand with the given task number.
     *
     * @param index The task number to be unmarked.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Unmarks the task in the task list and updates the storage file.
     *
     * @param tasks The task list to which the task is to be unmarked.
     * @param ui The user interface to interact with the user.
     * @param storage The storage file to be updated.
     * @throws DudeException If there is an error unmarking the task.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DudeException {
        tasks.markUndone(index);
        storage.saveTasks(tasks);
        ui.showUnmarked(tasks.getTask(index));
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
