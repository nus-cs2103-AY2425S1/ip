package tudee.command;

import tudee.task.TaskList;
import tudee.ui.Ui;
import tudee.storage.Storage;

public class ByeCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showBye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}