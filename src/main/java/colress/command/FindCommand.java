package colress.command;

import colress.TaskList;
import colress.Ui;

/**
 * Represents the find command which prints a list of tasks that contain a specified keyword
 */
public final class FindCommand extends ListCommand {
    public FindCommand() {
        super();
    }

    @Override
    public void execute(Ui ui, TaskList taskList) {
        ui.printTasks(taskList, "find");
    }
}
