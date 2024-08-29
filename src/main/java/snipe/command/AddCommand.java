package snipe.command;

import snipe.core.TaskList;
import snipe.exception.SnipeException;
import snipe.storage.Storage;
import snipe.task.Task;
import snipe.util.Ui;

import java.io.IOException;

/**
 * The {@code AddCommand} class represents a command to add a new task to the task list.
 * This command adds the specified task, saves the updated task list to storage, and
 * provides feedback to the user via the user interface.
 */
public class AddCommand extends Command{
    private final Task task;

    /**
     * Constructs an {@code AddCommand} with the specified task to be added.
     *
     * @param task The task to be added to the task list.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the add command by adding the task to the task list, saving the updated list to storage,
     * and displaying a confirmation message to the user.
     *
     * @param tasks   The task list to which the new task will be added.
     * @param ui      The user interface used to display messages to the user.
     * @param storage The storage object used to save the updated task list.
     * @throws SnipeException If an error occurs while saving the task list.
     * @throws IOException    If an input or output error occurs during saving.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SnipeException, IOException {
        tasks.addTask(this.task);
        storage.saveTaskList(tasks);
        ui.printWithLines(" Got it. I've added this task:\n  " + this.task + tasks.listLength());
    }
}
