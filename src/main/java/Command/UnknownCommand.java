package Command;

import Storage.Storage;
import TaskList.TaskList;
import UI.UI;

public class UnknownCommand extends Command{
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        ui.unknownLine();
    }
}
