package Majima.command;

import Majima.MajimaException;
import Majima.storage.Storage;
import Majima.task.Task;
import Majima.task.TaskList;
import Majima.ui.Ui;

public class UnmarkCommand extends Command {
    private int index;

    /**
     * Adds a UnmarkCommand object, which when executed, will unmark the corresponding
     * task as undone in the list of tasks and saves it to the .txt file.
     *
     * @param index The int index of the task in the list of tasks to be
     *              marked as undone.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MajimaException {
        try {
            Task task = tasks.get(index);
            task.markAsUndone();
            ui.showTaskUnmarked(task);
            storage.save(tasks.getTasks());
        } catch (IndexOutOfBoundsException e) {
            throw new MajimaException("Task number is out of range.");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
