package bob;

/**
 * Represents a command to delete a task from the task list by its index.
 * This command removes a specified task from the list.
 */
public class DeleteCommand extends Command {
    protected static String[] params = new String[] {
        "delete"
    };
    protected static int paramCount = 1;
    private final int idx;

    /**
     * Constructs a {@code DeleteCommand} with the specified task index.
     *
     * @param idx the index of the task to be deleted.
     */
    public DeleteCommand(int idx) {
        this.idx = idx;
    }

    /**
     * Executes the delete command by removing the task with the specified index from the task list.
     * Displays a message indicating that the task has been removed and shows the updated task list size.
     *
     * @param tasks the {@code TaskList} from which the task will be deleted.
     * @param ui the {@code Ui} to interact with the user.
     * @param storage the {@code Storage} for saving or loading tasks (not used in this method).
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        if (this.idx <= 0 || this.idx > tasks.getSize()) {
            throw new TaskIndexException(String.format("%d", this.idx));
        }
        Task deleted = tasks.delete(idx);
        return Printer.format(new String[] {"Noted. I've removed this task:",
            deleted.toString(),
            String.format("Now you have %d tasks in the list.", tasks.getSize())});
    }

    /**
     * Compares this {@code DeleteCommand} with another object for equality.
     * Two {@code DeleteCommand} objects are considered equal if they have the same task index.
     *
     * @param obj the object to be compared with this {@code DeleteCommand}.
     * @return {@code true} if the specified object is equal to this {@code DeleteCommand}; {@code false} otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof DeleteCommand temp) {
            return this.idx == temp.idx;
        }
        return false;
    }
}
