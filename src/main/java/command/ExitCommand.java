package command;

import command.Command;
import storage.Storage;
import task.TaskList;
import ui.Ui;

public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.displayGoodbye();
    }

    public boolean isExit() {
        return true;
    }
}
