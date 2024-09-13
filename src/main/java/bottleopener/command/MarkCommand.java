package bottleopener.command;

import bottleopener.task.Tasklist;
import bottleopener.ui.Ui;

/**
 * The {@code MarkCommand} class handles the marking and unmarking of tasks in the task list.
 * It marks a task as done or undone based on the provided flag.
 */
public class MarkCommand extends CommandWithIndex {
    private final boolean isMark;

    /**
     * Constructs a {@code MarkCommand} with the given task list, task index, and marking state.
     *
     * @param tasklist The list of tasks where the task to be marked/unmarked resides.
     * @param index The index of the task to be marked or unmarked.
     * @param isMark A flag indicating whether the task should be marked ({@code true}) or unmarked ({@code false}).
     */
    public MarkCommand(Tasklist tasklist, int index, boolean isMark) {
        super(tasklist, index);
        this.isMark = isMark;
    }

    /**
     * Executes the mark or unmark command based on the {@code isMark} flag.
     * If {@code isMark} is {@code true}, the task is marked as completed;
     * otherwise, it is unmarked.
     *
     * @return A string indicating the success of the operation, formatted for display in the UI.
     * @throws IllegalArgumentException if the provided task index is out of bounds.
     */
    @Override
    public String runCommand() throws IllegalArgumentException {
        try {
            if (isMark) {
                tasklist.markTask(index);
                return Ui.showMark();
            } else {
                tasklist.unmarkTask(index);
                return Ui.showUnmark();
            }
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException(Ui.showAppropriateNumberError());
        }

    }

}
