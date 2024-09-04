package chatbuddy.command;

import chatbuddy.exception.ChatBuddyException;
import chatbuddy.storage.Storage;
import chatbuddy.task.Task;
import chatbuddy.task.TaskList;
import chatbuddy.ui.Ui;

/**
 * Represents a command to delete a task from the task list.
 */

public class DeleteCommand extends Command {

    private int index;

    /**
     * Constructs a DeleteCommand with the specified index of the task to be deleted.
     *
     * @param index The index of the task to be deleted.
     * @throws ChatBuddyException If the provided index is not a valid number.
     */
    public DeleteCommand(String index) throws ChatBuddyException {
        try {
            this.index = Integer.parseInt(index) - 1;
        } catch (NumberFormatException e) {
            throw new ChatBuddyException("Invalid task number.");
        }
    }

    /**
     * Executes the delete command, removing the task from the task list and updating the storage.
     *
     * @param tasks   The task list.
     * @param ui      The user interface to display messages.
     * @param storage The storage to save the updated task list.
     * @throws ChatBuddyException If the task index is out of range.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ChatBuddyException {
        if (index >= tasks.size() || index < 0) {
            throw new ChatBuddyException("Task number out of range.");
        }
        Task removedTask = tasks.getTask(index);
        tasks.removeTask(index);
        storage.saveTasks(tasks.getTasks());
        ui.showDeleteTask(removedTask, tasks.size());
    }
}
