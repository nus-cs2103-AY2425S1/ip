package Nah.Command;

import Nah.Storage.Storage;
import Nah.TaskList.TaskList;
import Nah.UI.UI;

public class ListCommand extends Command{
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        ui.show(tasks.readTask());
    }
}
