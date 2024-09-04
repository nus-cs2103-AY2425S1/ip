package prince.command;

import prince.tasks.TaskList;
import prince.util.Storage;
import prince.util.Ui;

/**
 * Command that lists out tasks written into storage.
 */
public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTaskList(tasks);
    }
}
