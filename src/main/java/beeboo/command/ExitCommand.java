package beeboo.command;

import beeboo.components.Storage;
import beeboo.components.TaskList;
import beeboo.components.Ui;
import beeboo.exception.InvalidDateException;
import beeboo.exception.NoDescriptionException;

public class ExitCommand extends Command{
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidDateException, NoDescriptionException {
        ui.close();
        ui.byeMessageMessage();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
