package Majima.command;

import Majima.MajimaException;
import Majima.storage.Storage;
import Majima.task.Task;
import Majima.task.TaskList;
import Majima.ui.Ui;

public class DeleteCommand extends Command {
    private int index;

    /**
     * Adds a DeleteCommand object, which when executed, will delete the corresponding
     * index task in the list of tasks.
     *
     * @param index The int index of the task in the list of tasks to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MajimaException {
        Task removedTask = tasks.deleteTask(index);
        ui.showTaskDeleted(removedTask);
        storage.save(tasks.getTasks());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
