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
     * @param taskList The list of tasks to be cleared.
     * @param ui    The user interface to interact with the user.
     */
    @Override
    public String run(TaskList taskList, Ui ui) {
        for (int i = taskList.getCount() - 1; taskList.getCount() != 0; i--) {
            taskList.deleteTask(i);
        }
        return "Dayo~ All tasks has been cleared!";
    }
}
