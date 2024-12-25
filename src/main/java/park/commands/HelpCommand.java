package park.commands;

import park.storage.Storage;
import park.storage.TaskList;
import park.ui.Ui;

/**
 * Represents a command that shows the user a list of available commands
 */
public class HelpCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.setHelpResponse();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
