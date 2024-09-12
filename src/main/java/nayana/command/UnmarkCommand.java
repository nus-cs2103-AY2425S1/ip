package nayana.command;

import nayana.NayanaException;
import nayana.Storage;
import nayana.TaskList;
import nayana.Ui;
import nayana.task.Task;

/**
 * Command to unmark a task as not done.
 */
public class UnmarkCommand extends Command {
    private int index;

    /**
     * Constructs an UnmarkCommand with the specified task index.
     *
     * @param index The index of the task to unmark.
     */
    public UnmarkCommand(int index) {
        super();
        this.index = index;
    }

    /**
     * Unmarks the task at the specified index as not done.
     * Updates the storage with the new task list and informs the user.
     *
     * @param tasks   The TaskList containing the tasks.
     * @param ui      The Ui instance for user interaction.
     * @param storage The Storage instance for saving tasks.
     * @throws NayanaException If an error occurs while marking the task or writing to storage.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws NayanaException {
        assert tasks != null;
        Task task = tasks.markAsNotDone(index); // Marks task as not done in the task list.
        assert storage != null;
        storage.writeToFile(tasks.getTasks()); // Updates storage with the new list of tasks.
        assert ui != null;
        ui.printUnmarkTask(task); // Displays a confirmation message with the current task list
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
