package delta.command;

import delta.exception.DeltaException;
import delta.task.Task;
import delta.util.Storage;
import delta.util.TaskList;
import delta.util.Ui;

/**
 * Concrete subclass of Command abstract class.
 * Marks specific task from list as not done yet.
 */
public class UnmarkCommand extends Command {
    private int index;

    /**
     * Creates a UnmarkCommand instance.
     *
     * @param index Index of task to be unmarked in list.
     */
    public UnmarkCommand(int index) {
        super(CommandType.Unmark);
        this.index = index;
    }

    /**
     * Returns that UnmarkCommand is not the exit command.
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Marks specific task from list as not done yet.
     *
     * @param tasks List containing all current tasks.
     * @param ui User Interfacing object to print task unmarked message.
     * @param storage Storage object to save list after task unmarked.
     * @throws DeltaException If problem unmarking task from list or list unable to be saved.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DeltaException {
        Task task = tasks.unmarkTask(index);
        assert task.getStatusIcon().equals(" ") : "Task not unmarked yet";
        String message = "Nice! I've marked this task as not done yet:\n"
                + "  " + task;
        ui.showCommand(message);
        storage.save(tasks);
        return message;
    }
}
