package snipe.command;

import snipe.core.TaskList;
import snipe.exception.SnipeException;
import snipe.storage.Storage;
import snipe.task.Task;
import snipe.util.Ui;

import java.io.IOException;

/**
 * The {@code MarkCommand} class represents a command to mark a task in the task list as completed.
 * It updates the task status, saves the updated task list to storage, and provides feedback to the user via the user interface.
 */
public class MarkCommand extends Command{
    private int num;

    /**
     * Constructs a {@code MarkCommand} with the specified index of the task to be marked as completed.
     *
     * @param num The index of the task to be marked as completed (0-based index).
     */
    public MarkCommand(int num) {
        // Assert that the index passed in is non-negative
        assert num >= 0 : "Task index must be non-negative";
        this.num = num;
    }

    /**
     * Executes the mark command by changing the status of the specified task to "done" if it is not already marked,
     * saving the updated task list to storage, and returning a confirmation message to the user.
     * If the task is already marked as done, returns a message indicating that.
     *
     * @param tasks          The {@link TaskList} containing the task to be marked.
     * @param ui             The {@link Ui} instance used to display messages to the user.
     * @param storage        The {@link Storage} instance used to save the updated task list.
     * @param archiveTasks   The {@link TaskList}, not used in this command.
     * @param archiveStorage The {@link Storage} instance, not used in this command.
     * @return A message confirming the task has been marked as done, or a message indicating the task was already marked.
     * @throws SnipeException If the specified task index is out of range or if an error occurs while saving the task list.
     * @throws IOException    If an I/O error occurs during the saving process.
     */
    @Override
    public String getResponse(
            TaskList tasks,
            Ui ui,
            Storage storage,
            TaskList archiveTasks,
            Storage archiveStorage
    ) throws SnipeException, IOException {
        if (this.num > tasks.size() - 1) {
            throw new SnipeException("This list item does not exist!\n"
                    + tasks.listLength());
        } else {
            Task task = tasks.getTask(this.num);

            // Assert that the task exists and is not null (task retrieval is successful)
            assert task != null : "Task should not be null";

            if (!task.getStatus()) {
                task.changeStatus();
                String msg = "Nice! I've marked this task as done:\n" +
                        task.toString();
                storage.saveTaskList(tasks);
                return msg;
            } else {
                String msg = "This task is already marked done!";
                return msg;
            }
        }
    }

}
