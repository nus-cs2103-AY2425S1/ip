package LittleMissHelpful.Command;

import LittleMissHelpful.Storage;
import LittleMissHelpful.TaskList;
import LittleMissHelpful.Ui;
import LittleMissHelpful.Exception.InvalidCommandException;

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
