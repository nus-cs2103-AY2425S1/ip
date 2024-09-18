package commands;

import exceptions.DownyException;
import exceptions.InvalidFormatException;
import storage.Storage;
import tasks.Task;
import tasks.TaskList;
import ui.Ui;

/**
 * The {@code UnmarkCommand} class represents a command that, when executed, marks a task as
 * incomplete in the task list. The task is identified by its task number, which is provided
 * during the command's creation.
 */
public class UnmarkCommand implements Command {

    public final String taskNumber;

    /**
     * Constructs a new {@code UnmarkCommand} with the specified task number.
     *
     * @param taskNumber The task number to be marked as incomplete, as a string.
     */
    public UnmarkCommand(String taskNumber) {
        assert taskNumber != null : "Task number cannot be null.";
        this.taskNumber = taskNumber;
    }

    /**
     * Executes the Unmark command by marking the specified task as incomplete in the task list,
     * updating the storage, and displaying the updated task state via the user interface.
     *
     * @param storage The storage handler used for updating the task state in persistent storage.
     * @param tasks   The list of tasks currently in memory.
     * @param ui      The UI handler used for interacting with the user.
     * @throws DownyException If an error occurs during the execution of the command, such as
     *                        an invalid task number format or a task number that does not exist.
     */
    @Override
    public String execute(Storage storage, TaskList tasks, Ui ui) throws DownyException {
        try {
            int taskNum = Integer.parseInt(this.taskNumber);
            tasks.unmarkTaskInList(taskNum);
            Task t = tasks.getTask(taskNum - 1);
            storage.markIncomplete(t);
            return ui.displayIncompleteTask(t);
        } catch (NumberFormatException e) {
            throw new InvalidFormatException("Task number has to be a positive integer.");
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidFormatException("Task number does not exist.");
        }
    }

    /**
     * Returns the task number associated with this command.
     *
     * @return The task number to be marked as incomplete, as a string.
     */
    public String getTaskNumber() {
        return this.taskNumber;
    }
}
