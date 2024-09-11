package friday.command;

import friday.util.Storage;
import friday.util.TaskList;
import friday.util.Ui;

/**
 * Represents a command to exit the application.
 * Inherits from the Command class and implements the execute method to display a goodbye message.
 */
public class ExitCommand extends Command {

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        assert tasks != null : "TaskList should not be null";
        assert ui != null : "Ui should not be null";
        assert storage != null : "Storage should not be null";

        return ui.sayGoodbye();
    }

    @Override
    public boolean shouldExit() {
        return true;
    }
}
