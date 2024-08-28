package struggling.command;

import struggling.Storage;
import struggling.TaskList;
import struggling.Ui;

public class InvalidCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showError("This command in invalid!");
    }
}
