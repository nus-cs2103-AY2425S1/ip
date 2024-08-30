package lutodo.commands;

import lutodo.tasklist.TaskList;
import lutodo.storage.Storage;
import lutodo.ui.Ui;

public class ExitCommand extends Command{
    @Override
    public void execute(TaskList tasks, Storage storage) {
        Ui.showBye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
