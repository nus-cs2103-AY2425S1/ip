package lexi.command;

import lexi.exception.LexiException;
import lexi.storage.Storage;
import lexi.task.Task;
import lexi.task.TaskList;
import lexi.ui.Ui;

/**
 * Represents a command to delete a task from the task list.
 * When executed, this command removes a task based on its position in the list.
 */
public class DeleteCommand extends Command {
    private final int taskNumber;

    /**
     * Constructs a DeleteCommand with the specified task number.
     *
     * @param taskNumber The index of the task to be deleted.
     */
    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Executes the delete command, removing the specified task from the task list.
     * If the task is successfully deleted, the task list and storage are updated, and a confirmation message is shown.
     *
     * @param tasks   The list of tasks.
     * @param ui      The UI object to interact with the user.
     * @param storage The storage to update the task list.
     * @throws LexiException If the task number is invalid (i.e., if the task does not exist).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws LexiException {
        if (taskNumber <= 0 || taskNumber > tasks.getSize()) {
            throw new LexiException("You can't delete a task that doesn't exist");
        }
        Task removedTask = tasks.deleteTask(taskNumber - 1);
        ui.showDeleteMessage(removedTask, tasks.getSize());
        storage.updateStorage(tasks.getTasks());
    }

    /**
     * Returns the name of the command.
     *
     * @return The string "DELETE".
     */
    @Override
    public String getCommandName() {
        return "DELETE";
    }
}
