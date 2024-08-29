package commands;

import storage.Storage;
import tasks.TaskList;
import ui.Ui;

public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        super.setIsExit();
        ui.exit();
    }
}
