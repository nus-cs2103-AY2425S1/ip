package LittleMissHelpful.Command;

import LittleMissHelpful.Exception.InvalidCommandException;
import LittleMissHelpful.TaskList;
import LittleMissHelpful.Ui;
import LittleMissHelpful.Storage;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidCommandException {
        if (tasks.size() == 0) {
            ui.showNoTasks();
        } else {
            ui.showAllTasks(tasks.getTasks());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
