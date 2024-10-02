package nayana.command;

import nayana.NayanaException;
import nayana.Storage;
import nayana.TaskList;
import nayana.Ui;
import nayana.task.Task;

/**
 * Command to mark a task as done.
 */
public class MarkCommand extends Command {
    private final int index;

    /**
     * Constructs a MarkCommand with the specified index.
     *
     * @param index The index of the task to be marked as done.
     */
    public MarkCommand(int index) {
        super();
        this.index = index;
    }

    /**
     * Marks the task at the specified index as done.
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
        Task task = tasks.markAsDone(index); // Marks the Task as done in the task list.
        assert storage != null;
        storage.writeToFile(tasks.getTasks()); // Updates storage with the new list of tasks.
        assert ui != null;
        ui.printMarkTask(task); // Displays a confirmation message with the current task list.
    }

    /**
     * Determines if this command is an exit command.
     *
     * @return false as this command does not exit the application.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
