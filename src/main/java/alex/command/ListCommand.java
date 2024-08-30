package alex.command;

import alex.TaskList;
import alex.Ui;
import alex.Storage;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTasks(tasks, "\nHere are the tasks in your list: ");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
