package commands;

import exceptions.DownyException;
import exceptions.InvalidFormatException;
import storage.Storage;
import tasks.Task;
import tasks.TaskList;
import ui.Ui;

/**
 * The {@code DeleteCommand} class represents a command that, when executed, deletes a task
 * from the task list based on a specified task number. The command also updates the storage
 * and user interface to reflect the deletion.
 */
public class DeleteCommand implements Command {

    public final String taskNumber;

    /**
     * Constructs a new {@code DeleteCommand} with the specified task number.
     *
     * @param taskNumber The task number to be deleted, as a string.
     */
    public DeleteCommand(String taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Executes the Delete command by deleting the specified task from the task list,
     * removing it from storage, and displaying the deletion via the user interface.
     *
     * @param storage The storage handler used for deleting the task from persistent storage.
     * @param tasks   The list of tasks currently in memory.
     * @param ui      The UI handler used for interacting with the user.
     * @throws DownyException If an error occurs during the execution of the command, such as
     *                        an invalid task number format or a task number that does not exist.
     */
    @Override
    public String execute(Storage storage, TaskList tasks, Ui ui) throws DownyException {
        try {
            int taskNum = Integer.parseInt(this.taskNumber);
            Task t = tasks.deleteTaskInList(taskNum);
            storage.deleteTask(t);
            return ui.displayDeletedTask(t);
        } catch (NumberFormatException e) {
            throw new InvalidFormatException("Task number has to be a positive integer.");
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidFormatException("Task number does not exist.");
        }
    }

    /**
     * Returns the task number associated with this command.
     *
     * @return The task number to be deleted, as a string.
     */
    public String getTaskNumber() {
        return this.taskNumber;
    }
}

