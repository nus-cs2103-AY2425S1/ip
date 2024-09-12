package bob;

/**
 * Represents a command to unmark a specific task as not done.
 * This command updates the status of the task in the task list to incomplete when run.
 */
public class UnmarkCommand extends Command {
    protected static String[] params = new String[] {
        "unmark"
    };
    protected static int paramCount = 1;
    private final int idx;

    /**
     * Constructs an {@code UnmarkCommand} with the specified task index.
     *
     * @param idx the index of the task to unmark as not done.
     */
    public UnmarkCommand(int idx) {
        this.idx = idx;
    }

    /**
     * Executes the unmark command by marking the task at the specified index as not done.
     * Displays a confirmation message with the details of the unmarked task.
     *
     * @param tasks the {@code TaskList} containing tasks to be updated.
     * @param ui the {@code Ui} to interact with the user.
     * @param storage the {@code Storage} where tasks are saved.
     * @throws TaskIndexException if the index is out of bounds.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.unmark(idx);
        return Printer.format(new String[] {
            "OK, I've marked this task as not done yet:",
            tasks.describeTask(idx) });
    }

    /**
     * Checks if this {@code UnmarkCommand} is equal to another object.
     * Two {@code UnmarkCommand} instances are considered equal if they have the same index.
     *
     * @param obj the object to compare this command with.
     * @return {@code true} if the object is a {@code UnmarkCommand} with the same date; {@code false} otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof UnmarkCommand temp) {
            return this.idx == temp.idx;
        }
        return false;
    }
}
