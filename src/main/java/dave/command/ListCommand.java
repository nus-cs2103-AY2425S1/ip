package dave.command;

import dave.storage.Storage;
import dave.task.TaskList;
import dave.ui.Ui;

/**
 * Represents the command to list all tasks in the task list.
 * This command displays all the tasks currently stored in the task list.
 */
public class ListCommand extends Command {

    /**
     * Executes the list command.
     * This method returns all the tasks in the task list, numbered sequentially.
     *
     * @param tasks   The {@code TaskList} containing the tasks to be displayed.
     * @param storage The {@code Storage} object to handle saving the task list (not used in this command).
     * @param ui      The {@code Ui} object to handle user interaction (not used in this command).
     * @return A string representation of all tasks in the task list.
     */
    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) {
        assert tasks != null : "Task list should not be null.";
        assert tasks.getSize() >= 0 : "Task list size should be non-negative.";

        StringBuilder taskListString = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.getSize(); i++) {
            taskListString.append((i + 1)).append(". ").append(tasks.getTask(i)).append("\n");
        }
        return taskListString.toString().trim(); // Trim to remove the trailing new line
    }
}
