package friday.command;

import friday.task.TaskList;
import friday.util.Storage;
import friday.util.Ui;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTaskList(tasks);
    }
}
