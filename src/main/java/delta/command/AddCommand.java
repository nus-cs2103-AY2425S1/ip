package delta.command;

import delta.exception.DeltaException;
import delta.task.Task;
import delta.util.Storage;
import delta.util.TaskList;
import delta.util.Ui;

/**
 * Concrete subclass of Command abstract class.
 * Adds given task into stored list.
 */
public class AddCommand extends Command {
    private Task task;

    /**
     * Creates a AddCommand instance.
     *
     * @param task Task to be added into list.
     */
    public AddCommand(Task task) {
        super(CommandType.Add);
        this.task = task;
    }

    /**
     * Returns that AddCommand is not the exit command.
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Adds task into stored list.
     *
     * @param tasks List containing all current tasks.
     * @param ui User Interfacing object to print task added message.
     * @param storage Storage object to save list after task added.
     * @throws DeltaException If list unable to be saved.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DeltaException {
        tasks.addTask(task);
        assert tasks.getTasks().contains(task) : "Task not added into TaskList";
        String message = "Got it. I've added this task:\n"
                + "  " + task.toString() + "\n"
                + "Now you have " + tasks.getSize() + " task" + (tasks.getSize() > 1 ? "s" : "") + " in the list.";
        ui.showCommand(message);
        storage.save(tasks);
        return message;
    }
}
