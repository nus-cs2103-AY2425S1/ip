package muke.command;

import java.io.IOException;
import java.util.List;

import muke.storage.Storage;
import muke.task.Task;
import muke.task.TaskList;
import muke.ui.Ui;

/**
 * Represents a command to list all tasks in the task list.
 */
public class ListCommand implements Command {

    /**
     * Executes the list command, retrieving all tasks from the task list and displaying them to the user.
     *
     * @param tasks The task list containing all tasks.
     * @param ui The user interface to interact with the user.
     * @param storage The storage system of the application (not used in this command).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        List<Task> taskList = tasks.getTasksList(); // Get the list of tasks from TaskList
        ui.showTasks(taskList); // Use the UI to show the tasks
    }

    /**
     * Returns whether this command will terminate the application.
     *
     * @return false, as the list command does not terminate the application.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}