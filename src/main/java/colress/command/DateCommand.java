package colress.command;

import colress.TaskList;
import colress.Ui;

/**
 * Represents the date command that prints tasks from the list of tasks that fall on a specific date.
 */
public final class DateCommand extends ListCommand {
    public DateCommand() {
        super();
    }

    /**
     * Facilitates listing all tasks in the provided TaskList object that fall on a specified date, using the provided
     * Ui object to receive date input from the user and to print the output for the user.
     */
    @Override
    public void execute(Ui ui, TaskList taskList) {
        ui.printTasks(taskList, "date");
    }
}
