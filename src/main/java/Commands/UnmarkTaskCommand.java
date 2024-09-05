package Commands;

import Storage.Storage;
import TaskList.TaskList;
import UI.Ui;

/**
 * Command to unmark a specific task as undone.
 * This command processes a task identifier from the input and updates its status.
 *
 * @author jordanchan
 */
public class UnmarkTaskCommand extends Command {

    /**
     * Constructs an UnmarkTaskCommand with the given input string.
     *
     * @param s The input string that specifies which task to unmark as undone.
     */
    public UnmarkTaskCommand(String s) {
        super(s);
    }

    /**
     * Executes the command to unmark a task as undone.
     * This method extracts the task identifier from the input string, updates the task status,
     * writes the updated task list to storage, and updates the user interface to reflect the change.
     *
     * @param t  The task list where the task is located.
     * @param s  The storage object to save the updated task list.
     * @param ui The user interface to reflect the task status change.
     */
    @Override
    public String execute(TaskList t, Storage s, Ui ui) {
        // Extract the task identifier from the input string (assuming the task ID starts at index 7)
        int taskId = Integer.parseInt(String.valueOf(getInput().charAt(7)));

        // Unmark the task as undone
        t.markTaskAsUndone(taskId);

        // Write the updated task list to storage
        s.writeToHardDisk(t.getTasks());

        // Update the user interface to reflect the task status change
        return ui.markingTask(false, t.getTask(taskId));
    }
}

