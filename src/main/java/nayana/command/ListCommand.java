package nayana.command;

import nayana.Storage;
import nayana.TaskList;
import nayana.Ui;

/**
 * Command to list all tasks.
 */
public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        assert ui != null;
        ui.printTaskList(tasks); // Displays a confirmation message with the current task list

    }

    @Override
    public boolean isExit() {
        return false;
    }
}
