package vinegar.command;

import vinegar.storage.Storage;
import vinegar.task.TaskList;
import vinegar.ui.Ui;

/**
 * Displays the help page, providing guidance to the user on how to use the application.
 */
public class HelpCommand extends Command {

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showHelp();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
