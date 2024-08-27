package waterfall.command;

import waterfall.Storage;
import waterfall.Ui;
import waterfall.task.*;

public class ShowTasksCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTaskListMessage(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
