package command;

import tasks.TaskList;
import util.Storage;
import util.Ui;

/**
 * Command that lists out tasks written into storage.
 */
public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTaskList(tasks);
    }
}
