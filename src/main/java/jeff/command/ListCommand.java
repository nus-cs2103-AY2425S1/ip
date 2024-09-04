package jeff.command;

import jeff.Storage;
import jeff.TaskList;
import jeff.Ui;

/**
 * Represents a command that lists all tasks currently stored in the task list.
 * This command retrieves each task and displays it to the user, providing a complete view of all tasks.
 */
public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showLine();
        ui.showMessage("Here are your current tasks: ");
        for (int i = 0; i < tasks.size(); i++) {
            ui.showMessage((i + 1) + ". " + tasks.getTask(i));
        }
    }
}
