package Naega.Command;

import Naega.Storage.Storage;
import Naega.Task.TaskList;
import Naega.Ui.Ui;

/**
 * Represents a command to list all tasks in the task list.
 */
public class ListCommand extends Command {

    /**
     * Executes the command by displaying all tasks in the task list using the UI.
     *
     * @param tasks   the task list to display
     * @param ui      the UI component to display the tasks
     * @param storage the storage component (not used in this command)
     * @return
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        StringBuilder taskListString = new StringBuilder(ui.showLine() + "\nHere are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            taskListString.append(" ").append(i + 1).append(".").append(tasks.getTask(i)).append("\n");
        }
        taskListString.append(ui.showLine());

        return taskListString.toString();
    }
}