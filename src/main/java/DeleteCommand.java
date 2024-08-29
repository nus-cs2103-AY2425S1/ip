/**
 * The DeleteCommand class represents a command to delete a task in tasklist.
 */
public class DeleteCommand extends Command {
    private final int index;

    /**
     * Create a Delete command.
     * @param index
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Delete task.
     * @param tasklist
     * @param ui
     * @param storage
     * @throws ReminderebotException
     */
    @Override
    public void execute(TaskList tasklist, Ui ui, Storage storage) throws ReminderebotException {
        if (index > tasklist.length() || index < 1) { // index is out of bounds
            throw new ReminderebotException("Item selected to be deleted is not in list.\n" +
                    "Syntax: delete <int>");
        }
        Task task = tasklist.deleteTask(index);
        ui.deleteTask(task, tasklist.length());
    }

    /**
     * Delete command does not exit.
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
