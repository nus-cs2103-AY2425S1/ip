package main.LittleMissHelpful.Command;

import main.LittleMissHelpful.Exception.InvalidCommandException;
import main.LittleMissHelpful.TaskList;
import main.LittleMissHelpful.Ui;
import main.LittleMissHelpful.Storage;

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
