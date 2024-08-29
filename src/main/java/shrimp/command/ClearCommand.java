package shrimp.command;

import shrimp.task.TaskList;
import shrimp.utility.Ui;

/**
 * Represents a command to clear all tasks from the task list.
 */
public class ClearCommand implements Command {

    /**
     * Executes the clear command by removing all tasks from the task list.
     *
     * @param tasks The list of tasks to be cleared.
     * @param ui    The user interface to interact with the user.
     */
    @Override
    public void run(TaskList tasks, Ui ui) {
        for (int i = tasks.getCount() - 1; tasks.getCount() != 0; i--) {
            tasks.deleteTask(i);
        }
    }
}
