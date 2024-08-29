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
        this.num = num;
    }

    /**
     * Executes the mark command by changing the status of the specified task to "done" if it is not already marked,
     * saving the updated list to storage, and displaying a confirmation message to the user.
     * If the task is already marked as done, it displays a message indicating that.
     *
     * @param tasks   The task list containing the task to be marked.
     * @param ui      The user interface used to display messages to the user.
     * @param storage The storage object used to save the updated task list.
     * @throws SnipeException If the specified task index is out of range or an error occurs while saving the task list.
     * @throws IOException    If an input or output error occurs during saving.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SnipeException, IOException {
        if (this.num > tasks.size() - 1) {
            throw new SnipeException("This list item does not exist!\n"
                    + tasks.listLength());
        } else {
            Task task = tasks.getTask(this.num);
            if (!task.getStatus()) {
                task.changeStatus();
                String msg = "Nice! I've marked this task as done:\n" +
                        task.toString();
                storage.saveTaskList(tasks);
                ui.printWithLines(msg);
            } else {
                String msg = "This task is already marked done!";
                ui.printWithLines(msg);
            }
        }
    }
}
