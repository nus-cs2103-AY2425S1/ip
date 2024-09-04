package arsenbot.command;

import arsenbot.storage.Storage;
import arsenbot.task.TaskList;
import arsenbot.ui.Ui;

public class ExitCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showExit();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
