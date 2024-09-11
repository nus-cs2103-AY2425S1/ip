package park.commands;

import park.storage.Storage;
import park.storage.TaskList;
import park.ui.Ui;

/**
 * Represents a command that exits the chatbot session.
 */
public class ExitCommand extends Command{

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.setExitResponse();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
