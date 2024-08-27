/**
 * Concrete subclass of Command abstract class.
 * Adds given task into stored list.
 */
public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task task) {
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
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DeltaException {
        tasks.addTask(task);
        ui.showCommand("Got it. I've added this task:\n" +
                "\t   " + task.toString() + "\n" +
                "\t Now you have " + tasks.getSize() + " task" + (tasks.getSize() > 1 ? "s" : "") + " in the list.");
        storage.save(tasks);
    }
}
