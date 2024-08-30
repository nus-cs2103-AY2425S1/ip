package shrimp.command;

import shrimp.task.TaskList;
import shrimp.utility.Ui;

/**
 * Represents a command to list all tasks in the task list.
 */
public class ListCommand implements Command {

    /**
     * Executes the list command by printing all tasks in the task list
     * to the user interface.
     *
     * @param tasks The list of tasks to be displayed.
     * @param ui    The user interface to print the list of tasks.
     */
    @Override
    public void run(TaskList tasks, Ui ui) {
        ui.printTaskList(tasks);
    }
}
