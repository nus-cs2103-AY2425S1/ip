
package streams.command;

import streams.exception.StreamsException;
import streams.task.Task;
import streams.task.TaskList;
import streams.util.Storage;
import streams.util.Ui;

/**
 * Represents a command to mark a task as done or undone.
 */
public class MarkCommand extends Command {
    private int taskNumber;
    private boolean isDone;

    /**
     * Constructs a MarkCommand with the task index and done status.
     *
     * @param taskNumberStr The index of the task to mark.
     * @param isDone True to mark as done, false to mark as undone.
     */
    public MarkCommand(String taskNumberStr, boolean isDone) throws StreamsException {
        assert taskNumberStr != null : "Task number string cannot be null";
        assert !taskNumberStr.trim().isEmpty() : "Task number string cannot be empty";
        try {
            this.taskNumber = Integer.parseInt(taskNumberStr.trim()) - 1;
            assert this.taskNumber >= 0 : "Task number must be positive";
            this.isDone = isDone;
        } catch (NumberFormatException e) {
            throw new StreamsException("error parsing task number");
        }
    }

    /**
     * Executes the mark command, changing the done status of the specified task.
     *
     * @param tasks The task list containing the task to mark.
     * @param ui The user interface to display messages.
     * @param storage The storage to save updated task list.
     * @throws StreamsException If there's an error marking the task or saving the updated task list.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws StreamsException {
        assert tasks != null : "Tasks should not be null";
        assert ui != null : "Ui should not be null";
        assert storage != null : "Storage should not be null";
        Task task = tasks.getTask(taskNumber);
        if (isDone) {
            task.markAsDone();
            ui.showMessage("marked task done: " + task);
        } else {
            task.markAsNotDone();
            ui.showMessage("marked task not done: " + task);
        }
        storage.save(tasks.getTasks());
    }
}
