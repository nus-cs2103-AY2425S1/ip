package Commands;

import Exceptions.InvalidListItemException;
import Storage.Storage;
import TaskList.TaskList;
import Tasks.Task;
import UI.Ui;

/**
 * Command to delete a specific task from the task list.
 * This command processes a task identifier from the input and removes the corresponding task.
 */
public class DeleteTaskCommand extends Command {

    /**
     * Constructs a DeleteTaskCommand with the given input string.
     *
     * @param s The input string that specifies which task to delete.
     */
    public DeleteTaskCommand(String s) {
        super(s);
    }

    /**
     * Executes the command to delete a task from the task list.
     * This method extracts the task identifier from the input string, removes the task from the list,
     * writes the updated task list to storage, and updates the user interface to reflect the task removal.
     *
     * @param t  The task list where the task is located.
     * @param s  The storage object to save the updated task list.
     * @param ui The user interface to reflect the task removal.
     * @throws InvalidListItemException if the task identifier is invalid or the task cannot be found.
     */
    @Override
    public String execute(TaskList t, Storage s, Ui ui) throws InvalidListItemException {
        // Extract the task identifier from the input string (assuming the task ID starts at index 7)
        int taskId = Integer.parseInt(String.valueOf(getInput().charAt(7)));

        // Remove the task from the task list and get the removed task
        Task task = t.removeTask(taskId);

        // Write the updated task list to storage
        s.writeToHardDisk(t.getTasks());

        // Get the number of remaining tasks
        int remainingTasks = t.getTasks().size();

        // Update the user interface to reflect the task removal
        return ui.removingTask(task, remainingTasks);
    }
}

