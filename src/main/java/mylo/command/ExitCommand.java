package mylo.command;

import mylo.storage.StorageOperationException;
import mylo.task.TaskList;
import mylo.ui.Ui;

public class ExitCommand extends Command{
    @Override
    public void execute(TaskList list, Ui ui) {
        ui.exit();
    }
}
