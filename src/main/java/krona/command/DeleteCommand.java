package krona.command;

import krona.task.Task;
import krona.task.TaskList;
import krona.ui.Ui;
import krona.storage.Storage;
import krona.exception.KronaException;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    private int taskIndex;

    /**
     * Constructs a DeleteCommand with the specified task index.
     *
     * @param taskIndex The index of the task to be deleted from the task list.
     */
    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the command by deleting the task at the specified index from the task list,
     * showing relevant messages, and saving the updated task list to storage.
     *
     * @param tasks The task list that the command operates on.
     * @param ui The UI component that handles interactions with the user.
     * @param storage The storage component that handles saving the updated task list.
     * @throws KronaException If the task index is out of bounds or an error occurs while saving the task list.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws KronaException {
        try {
            Task task = tasks.get(taskIndex);
            tasks.deleteTask(taskIndex);
            ui.showMessage("Noted. I've removed this task:");
            ui.showMessage(task.toString());
            ui.showMessage("Now you have " + tasks.size() + " tasks in the list.");
            storage.save(tasks);  // Save tasks after deleting
        } catch (IndexOutOfBoundsException e) {
            throw new KronaException("krona.task.Task index is out of bounds.");
        }
    }
}