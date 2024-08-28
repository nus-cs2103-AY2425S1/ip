package struggling.command;

import struggling.Storage;
import struggling.TaskList;
import struggling.Ui;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.list(tasks.getTasksString());
    }
}
