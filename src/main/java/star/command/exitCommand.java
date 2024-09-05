package star.command;

import star.main.Storage;
import star.main.TaskList;
import star.main.Ui;

/**
 * Represents the Command to exit the application.
 * Inherits from the Command class.
 */
public class exitCommand extends Command {

    @Override
    public StringBuilder execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showBye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
