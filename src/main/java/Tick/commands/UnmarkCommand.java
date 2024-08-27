package Tick.commands;

import Tick.exceptions.TickException;
import Tick.storage.Storage;
import Tick.tasks.Task;
import Tick.tasks.TaskList;
import Tick.ui.Ui;

public class UnmarkCommand extends Command {
    private int toUnmark;

    public UnmarkCommand(int taskNumber) {
        this.toUnmark = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws TickException {
        Task undoneTask = tasks.markTaskAsDone(this.toUnmark);
        ui.showTaskMarked(undoneTask);
        storage.saveData(tasks.getAllTasks());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
