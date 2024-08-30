package krona.command;

import krona.task.Task;
import krona.task.TaskList;
import krona.ui.Ui;
import krona.storage.Storage;
import krona.exception.KronaException;

/**
 * Represents a command to mark a task as done in the task list.
 */
public class MarkCommand extends Command {
    private int taskIndex;

    /**
     * Constructs a MarkCommand with the specified task index.
     *
     * @param taskIndex The index of the task to be marked as done.
     */
    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the command by marking the task at the specified index as done,
     * showing relevant messages, and saving the updated task list to storage.
     *
     * @param tasks   The task list that the command operates on.
     * @param ui      The UI component that handles interactions with the user.
     * @param storage The storage component that handles saving the updated task list.
     * @throws KronaException If the task index is out of bounds or an error occurs while saving the task list.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws KronaException {
        try {
            Task task = tasks.get(taskIndex);
            task.markDone();
            ui.showMessage("Nice! I've marked this task as done:");
            ui.showMessage(task.toString());
            storage.save(tasks);
        } catch (IndexOutOfBoundsException e) {
            throw new KronaException("krona.task.Task index is out of bounds.");
        }
    }
}