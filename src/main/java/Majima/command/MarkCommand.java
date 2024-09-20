package Majima.command;

import Majima.MajimaException;
import Majima.storage.Storage;
import Majima.task.Task;
import Majima.task.TaskList;
import Majima.ui.Ui;

public class MarkCommand extends Command {
    private int index;

    /**
     * Adds a MarkCommand object, which when executed, will mark the corresponding
     * task as Done in the list of tasks and saves it to the .txt file.
     *
     * @param index The int index of the task in the list of tasks to be
     *              marked as done.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MajimaException {
        try {
            Task task = tasks.get(index);
            task.markAsDone();
            ui.showTaskMarked(task);
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
