/**
 * Concrete subclass of Command abstract class.
 * Deletes specific task from stored list.
 */
public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
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
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DeltaException {
        Task task = tasks.deleteTask(index);
        ui.showCommand("Noted. I've removed this task:\n" +
                "\t   " + task + "\n" +
                "\t Now you have " + tasks.getSize() + " tasks" + " in the list.");
        storage.save(tasks);
    }
}
