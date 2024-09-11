package delta.command;

import delta.exception.DeltaException;
import delta.task.Task;
import delta.util.Storage;
import delta.util.TaskList;
import delta.util.Ui;

/**
 * Concrete subclass of Command abstract class.
 * Deletes specific task from stored list.
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Creates a DeleteCommand instance.
     *
     * @param index Index of task to be deleted from list.
     */
    public DeleteCommand(int index) {
        super(CommandType.Delete);
        this.index = index;
    }

    /**
     * Returns that DeleteCommand is not the exit command.
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Deletes specific task from list.
     *
     * @param tasks List containing all current tasks.
     * @param ui User Interfacing object to print task deleted message.
     * @param storage Storage object to save list after task deleted.
     * @throws DeltaException If problem deleting task from list or list unable to be saved.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DeltaException {
        Task task = tasks.deleteTask(index);
        assert !tasks.getTasks().contains(task) : "Task still exists in TaskList";
        String message = "Noted. I've removed this task:\n"
                + "  " + task + "\n"
                + "Now you have " + tasks.getSize() + " task" + (tasks.getSize() > 1 ? "s" : "") + " in the list.";
        ui.showCommand(message);
        storage.save(tasks);
        return message;
    }
}
