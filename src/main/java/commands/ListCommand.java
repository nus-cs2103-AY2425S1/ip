package commands;

import exceptions.DownyException;
import storage.Storage;
import tasks.TaskList;
import ui.Ui;

public class ListCommand implements Command {
    @Override
    public void execute(Storage storage, TaskList tasks, Ui ui) throws DownyException {
        tasks.loadTasks(storage);
        if (tasks.isEmpty()) {
            Ui.showMessage("There are no tasks!");
            return;
        }
        ui.displayTasks(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
