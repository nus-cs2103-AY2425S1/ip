package charlotte.command;

import charlotte.command.Command;
import charlotte.storage.Storage;
import charlotte.task.TaskList;
import charlotte.ui.Ui;

public class ExitCommand extends Command {
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        this.isExit = true;
        ui.printExit();
    }
}
