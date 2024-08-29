package snipe.command;

import snipe.exception.SnipeException;
import snipe.storage.Storage;
import snipe.core.TaskList;
import snipe.util.Ui;

public class ExitCommand extends Command{
    @Override
    public boolean isExit() {
        return true;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SnipeException {
        String CLOSING_MESSAGE = "Bye. Hope to see you again soon!";
        ui.printWithLines(CLOSING_MESSAGE);
    }
}
