package bangmang.command;

import bangmang.storage.Storage;
import bangmang.tasks.TaskList;
import bangmang.ui.Ui;
import bangmang.exception.InvalidCommandException;

public class ListCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws InvalidCommandException {
        if (tasks.size() == 0) {
            return ui.showNoTasks();
        } else {
            return ui.showAllTasks(tasks.getTasks());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
