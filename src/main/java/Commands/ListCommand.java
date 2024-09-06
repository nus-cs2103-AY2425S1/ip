package Commands;

import Tasks.TaskList;
import UI.UI;
import Storage.Storage;

public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        ui.showTaskList(tasks.getTaskList());
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
