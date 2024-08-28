package tick.commands;

import tick.exceptions.TickException;
import tick.storage.Storage;
import tick.storage.TaskList;
import tick.ui.Ui;

public class IncorrectCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws TickException {
        throw new TickException("I don't know what that means!");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
