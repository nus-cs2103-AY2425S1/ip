package command;

import tasklist.TaskList;
import tasklist.TaskListOutOfBoundsException;
import tasks.Task;
import ui.CommandLineUi;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    /** The index of the task to be deleted. */
    protected int index;

    /**
     * Constructs a DeleteCommand with the specified index.
     *
     * @param index The index of the task to be deleted from the task list.
     */
    public DeleteCommand(int index) {
        this.index = index;

    }

    /**
     * Executes the command by deleting the task at the specified index from the task list
     * and displaying the appropriate messages in the command line interface.
     *
     * @param tasklist The TaskList from which the task will be deleted.
     * @param ui       The CommandLineUI used to interact with the user.
     */
    public void execute(TaskList tasklist, CommandLineUi ui) {

        try {
            Task task = tasklist.delete(index);

            ui.speakLine("Noted. I've removed this task:");
            ui.speakLine("  " + task);
            ui.speakLine("Now you have " + tasklist.size() + " tasks in the list.");

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
