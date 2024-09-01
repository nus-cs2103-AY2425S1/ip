package Gary.command;

import Gary.TaskList;
import Gary.Ui;
import Gary.Storage;

public class ShowListCommand extends Command {
    @Override
    public void execute(TaskList taskLists, Ui ui, Storage storage) {
        ui.showTaskLists(taskLists);
    }
    @Override
    public boolean isBye() {
        return false;
    }
}

