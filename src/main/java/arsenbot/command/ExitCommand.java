package arsenbot.command;

import arsenbot.storage.Storage;
import arsenbot.task.TaskList;
import arsenbot.ui.Ui;

public class ExitCommand extends Command {

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showExit();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
