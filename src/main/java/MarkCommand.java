/**
 * Represent the command to mark a task in the task list.
 * This command implements the Command interface and is for
 * marking a single task to the task list and displaying the confirmation message
 * that the task has been successfully marked or unmarked to the user.
 */
public class MarkCommand implements Command {
    /** The index of the task in the task list to be marked. */
    private final int index;
    /** The boolean to determine whether to mark or unmark the task. */
    private final boolean isMark;

    /**
     * Constructs an MarkCommand with the specified task and mark operation to be done.
     *
     * @param index The index of the task object in the task list to be marked.
     * @param isMark The indicator of the mark operation to be done.
     */
    public MarkCommand(int index, boolean isMark) {
        this.index = index;
        this.isMark = isMark;
    }

    /**
     * @inheritDoc
     *
     * Marks or unmarks the specified task in the task list and display a confirmation message.
     *
     * @param tasks The TaskList which the specified task will be added.
     * @param ui The Ui object used to display the confirmation message.
     */
    @Override
    public void execute(TaskList tasks, Ui ui) {
        if (isMark) {
            tasks.markAsDone(index);
            ui.displayTaskStatus(tasks.getTask(index));
        } else {
            tasks.markAsNotDone(index);
            ui.displayTaskStatus(tasks.getTask(index));
        }

    }
}
