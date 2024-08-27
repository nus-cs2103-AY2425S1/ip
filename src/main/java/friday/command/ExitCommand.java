package friday.command;

import friday.Storage;
import friday.TaskList;
import friday.Ui;

/**
 * Represents a command to exit the application.
 * Inherits from the Command class and implements the execute method to display a goodbye message.
 */
public class ExitCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.sayGoodbye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}