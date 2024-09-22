package streams.command;

import streams.exception.StreamsException;
import streams.task.Task;
import streams.task.TaskList;
import streams.util.Storage;
import streams.util.Ui;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    private int taskNumber;

    /**
     * Constructs a DeleteCommand with the index of the task to be deleted.
     *
     * @param taskNumberStr The index of the task to be deleted.
     * @throws StreamsException If there's an error executing the command.
     */
    public DeleteCommand(String taskNumberStr) throws StreamsException {
        try {
            this.taskNumber = Integer.parseInt(taskNumberStr.trim()) - 1;
        } catch (NumberFormatException e) {
            throw new StreamsException("error parsing task number");
        }
    }

    /**
     * Executes the delete command, removing the specified task from the task list.
     *
     * @param tasks The task list to delete the task from.
     * @param ui The user interface to display messages.
     * @param storage The storage to save updated task list.
     * @throws StreamsException If there's an error deleting the task or saving the updated task list.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws StreamsException {
        Task removedTask = tasks.getTask(taskNumber);
        tasks.deleteTask(taskNumber);
        ui.showMessage("okkieee..i've removed this task: " + removedTask);
        ui.showMessage("yayyayayy!!!! now you have " + tasks.size() + " tasks in the list");
        storage.save(tasks.getTasks());
    }
}
