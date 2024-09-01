package king.commands;

import king.KingException;
import king.Storage;
import king.TaskList;
import king.Ui;

public class InvalidCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showError("Invalid command.");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
