package command;

import tasklist.TaskList;
import tasklist.TaskListOutOfBoundsException;
import ui.CommandLineUi;

/**
 * Represents a command to unmark a task as not done in the task list.
 */
public class UnmarkCommand extends Command {
    /** The index of the task to be unmarked as not done. */
    protected int index;

    /**
     * Constructs an UnmarkCommand with the specified task index.
     *
     * @param index The index of the task to be unmarked as not done in the task list.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the command by unmarking the task at the specified index as not done in the task list,
     * and displaying the appropriate messages in the command line interface.
     *
     * @param tasklist The TaskList where the task will be unmarked as not done.
     * @param ui       The CommandLineUI used to interact with the user.
     */
    public void execute(TaskList tasklist, CommandLineUi ui) {
        try {
            tasklist.unmark(index);

            ui.speakLine("OK, I've marked this task as not done yet: ");
            ui.speakLine("  " + tasklist.getTask(index));
        } catch (TaskListOutOfBoundsException e) {
            ui.speakLine(e.getMessage());
        }
    }

    /**
     * Indicates whether this command causes the application to exit.
     *
     * @return false, as this command does not cause the application to exit.
     */
    public boolean isExit() {
        return false;
    }
}
