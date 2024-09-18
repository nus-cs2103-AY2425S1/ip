package delta.command;

import delta.exception.DeltaException;
import delta.task.Task;
import delta.util.Storage;
import delta.util.TaskList;
import delta.util.Ui;

/**
 * Concrete subclass of Command abstract class.
 * Marks specific task from list as done.
 */
public class MarkCommand extends Command {
    private int index;

    /**
     * Creates a MarkCommand instance.
     *
     * @param index Index of task to be marked as done in list.
     */
    public MarkCommand(int index) {
        super(CommandType.Mark);
        this.index = index;
    }

    /**
     * Returns that MarkCommand is not the exit command.
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Marks specific task from list as done.
     *
     * @param tasks List containing all current tasks.
     * @param ui User Interfacing object to print task marked message.
     * @param storage Storage object to save list after task marked.
     * @throws DeltaException If problem marking task from list or list unable to be saved.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DeltaException {
        Task task = tasks.markTask(index);
        assert task.getStatusIcon().equals("X") : "Task not marked as done";
        String message = "Nice! I've marked this task as done:\n"
                + "  " + task;
        ui.showCommand(message);
        storage.save(tasks);
        return message;
    }
}
