package barry.command;

import barry.util.Storage;
import barry.util.TaskList;
import barry.util.Ui;

/**
 * Represents a command given by users to give in-app guidance on the commands to use.
 */
public class HelpCommand extends Command {
    @Override
    public void execute(TaskList allTasks, Ui ui, Storage storage) {
        String helpMessage = ui.showHelp();
        ui.showMessage(helpMessage);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
