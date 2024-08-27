package Nah.Command;

import Nah.Storage.Storage;
import Nah.TaskList.TaskList;
import Nah.UI.UI;

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
