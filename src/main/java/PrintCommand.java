/**
 * Concrete subclass of Command abstract class.
 * Prints all tasks from stored list.
 */
public class PrintCommand extends Command {
    public PrintCommand() {
        super(CommandType.Print);
    }
    /**
     * Returns that MarkCommand is not the exit command.
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Prints all tasks from list.
     *
     * @param tasks List containing all current tasks.
     * @param ui User Interfacing object to print all tasks.
     * @param storage Storage object to save list.
     * @throws DeltaException If list is empty.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DeltaException {
        if (tasks.getSize() == 0) {
            throw new DeltaException("There are no tasks in your list.");
        } else {
            ui.showCommand("Here are the tasks in your list:");
        }
        for (int i = 0; i < tasks.getSize(); i++) {
            ui.showCommand(String.format("%d.%s", i + 1, tasks.getTask(i)));
        }
    }
}
