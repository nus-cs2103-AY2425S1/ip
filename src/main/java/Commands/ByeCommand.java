package Commands;

import Storage.Storage;
import Tasks.TaskList;
import UI.UI;

public class ByeCommand extends Command {
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        ui.showBye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
