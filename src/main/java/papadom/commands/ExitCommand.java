package papadom.commands;

import papadom.Storage.Storage;
import papadom.Storage.TaskList;
import papadom.Ui;

public class ExitCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.exitMessage();
    }
}
