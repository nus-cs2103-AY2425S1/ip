package krona.command;

import krona.exception.KronaException;
import krona.storage.Storage;
import krona.task.Task;
import krona.task.TaskList;
import krona.ui.Ui;

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

            StringBuilder output = new StringBuilder();
            output.append("Noted. I've removed this task:\n");
            output.append(task.toString()).append("\n");
            output.append("Now you have ").append(tasks.size()).append(" tasks in the list.");

            storage.save(tasks);

            ui.setCombinedMessage(output.toString());

        } catch (IndexOutOfBoundsException e) {
            throw new KronaException("krona.task.Task index is out of bounds.");
        }
    }
}
