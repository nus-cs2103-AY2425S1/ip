package command;

import tasklist.TaskList;
import tasklist.TaskListOutOfBoundsException;
import ui.CommandLineUi;

/**
 * Represents a command to mark a task as done in the task list.
 */
public class MarkCommand extends Command {
    /** The index of the task to be marked as done. */
    protected int index;

    /**
     * Constructs a MarkCommand with the specified task index.
     *
     * @param index The index of the task to be marked as done in the task list.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the command by marking the task at the specified index as done in the task list,
     * and displaying the appropriate messages in the command line interface.
     *
     * @param tasklist The TaskList where the task will be marked as done.
     * @param ui       The CommandLineUI used to interact with the user.
     */
    public void execute(TaskList tasklist, CommandLineUi ui) {
        try {
            tasklist.mark(index);

            ui.speakLine("Nice! I've marked this task as done: ");
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
