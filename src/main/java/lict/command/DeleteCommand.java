package lict.command;

import lict.LictException;
import lict.Storage;
import lict.TaskList;
import lict.Ui;
import lict.task.Task;

/**
 * The {@code DeleteCommand} class handles the deletion of a task from the task list.
 * It takes a task number as input, removes the corresponding task from the list,
 * and updates the UI and storage to reflect this change.
 */
public class DeleteCommand extends Command {
    private String taskNum;

    public DeleteCommand(String taskNum) {
        this.taskNum = taskNum;
    }

    /**
     * Executes the command to delete a task from the task list.
     * Validates the task number and, if valid, deletes the corresponding task.
     *
     * @param tasks    The task list from which the task will be deleted.
     * @param ui       The UI component responsible for displaying the deletion result.
     * @param storage  The storage to save the updated task list to.
     * @throws LictException If the task number is invalid or not a valid integer.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws LictException {
        try {
            int index = Integer.parseInt(taskNum) - 1;
            if (index < 0) {
                throw new LictException("Invalid task number. lict.task.Task numbers should all be positive.");
            } else if (index >= tasks.size()) {
                throw new LictException("Invalid task number. There are only " + tasks.size() + " tasks in the list.");
            } else {
                Task t = tasks.deleteTask(index);
                ui.hasDeletedTask(t, tasks.size());
                storage.saveTasks(tasks);
            }
        } catch (NumberFormatException e) {
            throw new LictException("Please enter a valid integer index. For eg. 'delete 1'");
        }
    }
}
