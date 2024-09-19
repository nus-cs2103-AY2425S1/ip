package barcus.command;

import barcus.storage.Storage;
import barcus.tasklist.TaskList;

/**
 * Command to list all tasks in the task list.
 */
public class ListCommand extends Command {
    /**
     * Executes the command by displaying all tasks in the task list.
     * If no tasks are present, it provides a message indicating that no tasks are stored.
     *
     * @param tasks the task list containing tasks to be displayed
     * @param storage the storage object (not used in this command)
     */
    @Override
    public void execute(TaskList tasks, Storage storage) {
        if (tasks.getLength() == 0) {
            output = "No tasks stored yet, use todo, deadline or event to add a task!";
        } else {
            output = "Okie, here are your tasks!\n" + tasks.getTaskListDisplay();
        }
    }

    /**
     * Returns whether this command causes the application to exit.
     *
     * @return false, as listing tasks does not cause the application to exit
     */
    @Override
    public boolean isExit() {
        return false;
    }

}
