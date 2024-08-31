package colress.command;

import colress.TaskList;
import colress.Ui;

/**
 * Represents the list command that prints all tasks from the list of tasks.
 */
public class ListCommand extends Command {
    public ListCommand() {
        super("");
    }

    /**
     * Facilitates listing all tasks in the provided TaskList object, and using the provided Ui object to print the
     * output for the user.
     */
    @Override
    public void execute(Ui ui, TaskList taskList) {
        ui.printTasks(taskList, true);
    }
}
