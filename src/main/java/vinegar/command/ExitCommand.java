package vinegar.command;

import vinegar.TaskList;
import vinegar.command.Command;
import vinegar.storage.Storage;
import vinegar.ui.Ui;

public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showGoodbye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
