package skywalker.command;

import skywalker.storage.Storage;
import skywalker.task.TaskList;
import skywalker.ui.Ui;

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
