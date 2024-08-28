package monique.command;

import monique.exception.MoniqueException;
import monique.exception.UnknownCommandException;
import monique.storage.Storage;
import monique.tasklist.TaskList;
import monique.ui.Ui;

public class UnknownCommand extends Command {
    @Override
    public boolean isActive() {
        return true;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MoniqueException {
        throw new UnknownCommandException();
    }
}
