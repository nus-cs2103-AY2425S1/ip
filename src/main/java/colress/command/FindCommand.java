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
    /**
     * Facilitates listing all tasks in the provided TaskList object that contain a specified keyword,
     * using the provided Ui object to receive date input from the user and to print the output for the user.
     */
    @Override
    public void execute(Ui ui, TaskList taskList) {
        ui.printTasks(taskList, "find");
    }
}
