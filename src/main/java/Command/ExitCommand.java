package Command;

import Storage.Storage;
import TaskList.TaskList;
import UI.UI;

public class ExitCommand extends Command{
    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public void execute(TaskList task, UI ui, Storage storage) {
        ui.exit();
    }

}
