package command;

import storage.StorageOperationException;
import task.TaskList;
import ui.Ui;

public class ExitCommand extends Command{
    @Override
    public void execute(TaskList list, Ui ui) {
        ui.exit();
    }
}
