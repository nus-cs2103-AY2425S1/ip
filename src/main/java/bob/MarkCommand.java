package bob;

/**
 * Represents a command to mark a specific task as done.
 * This command updates the status of the task in the task list to completed when run.
 */
public class MarkCommand extends Command {
    protected static String[] params = new String[] {
        "mark"
    };
    protected static int paramCount = 1;
    private final int idx;

    /**
     * Constructs a {@code MarkCommand} with the specified task index.
     *
     * @param idx the index of the task to mark as done.
     */
    public MarkCommand(int idx) {
        this.idx = idx;
    }

    /**
     * Executes the mark command by marking the task at the specified index as done.
     * Displays a confirmation message with the details of the marked task.
     *
     * @param tasks the {@code TaskList} containing tasks to be updated.
     * @param ui the {@code Ui} to interact with the user.
     * @param storage the {@code Storage} where tasks are saved (not used in this method).
     * @throws TaskIndexException if the index is out of bounds.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.mark(idx);
        return Printer.format(new String[] {
            "Nice! I've marked this task as done:",
            tasks.describeTask(idx) });
    }

    /**
     * Checks if this {@code MarkCommand} is equal to another object.
     * Two {@code MarkCommand} instances are considered equal if they have the same index.
     *
     * @param obj the object to compare this command with.
     * @return {@code true} if the object is a {@code MarkCommand} with the same date; {@code false} otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof MarkCommand temp) {
            return this.idx == temp.idx;
        }
        return false;
    }
}
