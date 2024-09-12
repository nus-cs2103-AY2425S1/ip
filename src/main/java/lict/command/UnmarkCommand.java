package lict.command;

import lict.LictException;
import lict.Storage;
import lict.TaskList;
import lict.Ui;
import lict.task.Task;

/**
 * The {@code UnmarkCommand} class represents a command to unmark a task as not done.
 * It extends the {@code Command} class and overrides the {@code execute}
 * method to implement the unmarking functionality.
 */
public class UnmarkCommand extends Command {
    private String taskNum;

    /**
     * Constructs an {@code UnmarkCommand} with the specified task number.
     *
     * @param taskNum The task number to be unmarked.
     */
    public UnmarkCommand(String taskNum) {
        this.taskNum = taskNum;
    }

    /**
     * Executes the command to unmark a task as not done.
     * Validates the task number, unmarks the corresponding task, and updates the UI and storage.
     *
     * @param tasks    The task list containing the tasks.
     * @param ui       The UI component responsible for displaying the updated task status.
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
                Task t = tasks.get(index);
                t.isMarked(false);
                ui.hasUnmarkedTask(t);
                storage.saveTasks(tasks);
            }
        } catch (NumberFormatException e) {
            throw new LictException("Please enter a valid integer index. For eg. 'unmark 1'");
        }
    }
}
