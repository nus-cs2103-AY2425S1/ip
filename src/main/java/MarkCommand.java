/**
 * Concrete subclass of Command abstract class.
 * Marks specific task from list as done.
 */
public class MarkCommand extends Command {
    private int index;

    public MarkCommand(int index) {
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
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DeltaException {
        Task task = tasks.markTask(index);
        ui.showCommand("Nice! I've marked this task as done:\n" +
                "\t   " + task);
        storage.save(tasks);
    }
}
