package snipe.command;

import snipe.exception.SnipeException;
import snipe.storage.Storage;
import snipe.core.TaskList;
import snipe.task.Task;
import snipe.util.Ui;

import java.io.IOException;

/**
 * The {@code DeleteCommand} class represents a command to delete a task from the task list.
 * It removes the specified task, saves the updated task list to storage, and provides feedback to the user via the user interface.
 */
public class DeleteCommand extends Command{
    private int num;

    /**
     * Constructs a {@code DeleteCommand} with the specified index of the task to be deleted.
     *
     * @param num The index of the task to be deleted (0-based index).
     */
    public DeleteCommand(int num) {
        this.num = num;
    }

    /**
     * Executes the delete command by removing the specified task from the task list, saving the updated list to storage,
     * and displaying a confirmation message to the user.
     *
     * @param tasks   The task list from which the task will be deleted.
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
            Task toRemove = tasks.getTask(this.num);
            tasks.deleteTask(this.num);
            storage.saveTaskList(tasks);
            String message = "Noted. I've removed this task:\n"
                    + toRemove.toString()
                    + tasks.listLength();
            ui.printWithLines(message);
        }
    }
}
