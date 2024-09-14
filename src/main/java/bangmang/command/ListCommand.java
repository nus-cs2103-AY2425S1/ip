package LittleMissHelpful.Command;

import LittleMissHelpful.Storage.Storage;
import LittleMissHelpful.Tasks.TaskList;
import LittleMissHelpful.Ui.Ui;
import LittleMissHelpful.Exception.InvalidCommandException;

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
