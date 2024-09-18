package struggling.command;

import struggling.Storage;
import struggling.TaskList;
import struggling.Ui;

/**
 * InvalidCommand specifies instruction when command is not valid.
 */
public class InvalidCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showError("This command in invalid!");
    }
}
