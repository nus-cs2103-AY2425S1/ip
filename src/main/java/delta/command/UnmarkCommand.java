package delta.command;

import delta.util.TaskList;
import delta.util.Ui;
import delta.util.Storage;

import delta.task.Task;

import delta.exception.DeltaException;

/**
 * Concrete subclass of Command abstract class.
 * Marks specific task from list as not done yet.
 */
public class UnmarkCommand extends Command {
    private int index;

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
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DeltaException {
        Task task = tasks.unmarkTask(index);
        ui.showCommand("Nice! I've marked this task as not done yet:\n" +
                "\t   " + task);
        storage.save(tasks);
    }
}
