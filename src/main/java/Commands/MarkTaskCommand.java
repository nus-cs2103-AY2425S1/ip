package Commands;

import Storage.Storage;
import TaskList.TaskList;
import UI.Ui;

/**
 * Command to mark a specific task in the tasklist object as done.
 * This command processes a task identifier from the input and updates its status.
 *
 * @author jordanchan
 */
public class MarkTaskCommand extends Command {

    /**
     * Constructs a MarkTaskCommand with the given input string.
     *
     * @param s The input string that specifies which task to mark as done.
     */
    public MarkTaskCommand(String s) {
        super(s);
    }

    /**
     * Executes the command to mark a task as done.
     * This method parses the task identifier from the input string, updates the task status,
     * writes the updated task list to storage, and updates the user interface.
     *
     * @param t  The task list where the task is located.
     * @param s  The storage object to save the updated task list.
     * @param ui The user interface to reflect the task status change.
     */
    @Override
    public String execute(TaskList t, Storage s, Ui ui) {
        // Parse the task identifier from the input string
        int taskId = Integer.parseInt(String.valueOf(getInput().charAt(5)));

        // Mark the task as done
        t.markTaskAsDone(taskId);

        // Write the updated task list to storage
        s.writeToHardDisk(t.getTasks());

        // Update the user interface to reflect the task status change
        return ui.markingTask(true, t.getTask(taskId));
    }
}

