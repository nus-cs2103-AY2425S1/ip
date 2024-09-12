package lict.command;

import lict.LictException;
import lict.Storage;
import lict.TaskList;
import lict.Ui;
import lict.task.Task;

/**
 * The {@code MarkCommand} class represents a command to mark a task as done.
 * It extends the {@code Command} class and overrides the {@code execute} method to implement the marking functionality.
 */
public class MarkCommand extends Command {
    private String taskNum;

    /**
     * Constructs a {@code MarkCommand} with the specified task number.
     *
     * @param taskNum The task number to be marked as done.
     */
    public MarkCommand(String taskNum) {
        this.taskNum = taskNum;
    }

    /**
     * Executes the command to mark a task as done.
     * Validates the task number, marks the corresponding task as done, and updates the UI and storage.
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
                t.isMarked(true);
                ui.hasMarkedTask(t);
                storage.saveTasks(tasks);
            }
        } catch (NumberFormatException e) {
            throw new LictException("Please enter a valid integer index. For eg. 'mark 1'");
        }
    }
}
