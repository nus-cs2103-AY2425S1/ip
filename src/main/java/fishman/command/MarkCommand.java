package fishman.command;

import fishman.task.TaskList;
import fishman.utils.Ui;

/**
 * Represents the command to mark a task in the task list.
 * This command implements the Command interface and is for
 * marking a single task to the task list and returns the confirmation message
 * that the task has been successfully marked or unmarked to the user.
 */
public class MarkCommand implements Command {
    /** The index of the task in the task list to be marked. */
    private final int index;
    /** The boolean to determine whether to mark or unmark the task. */
    private final boolean isMark;

    /**
     * Constructs a MarkCommand with the specified task and mark operation to be done.
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
     *      Marks or unmarks the specified task in the task list and returns a confirmation message.
     *
     * @param tasks The TaskList which the specified task will be added.
     * @param ui The Ui object used to generate the confirmation message.
     * @return The confirmation message indicating the command execution.
     */
    @Override
    public String execute(TaskList tasks, Ui ui) {
        if (isMark) {
            tasks.markAsDone(index);
        } else {
            tasks.markAsNotDone(index);
        }
        return ui.getTaskStatusMessage(tasks.getTask(index));
    }
}
