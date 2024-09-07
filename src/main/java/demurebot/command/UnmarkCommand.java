package demurebot.command;

import demurebot.task.Task;
import demurebot.task.TaskList;
import demurebot.ui.Ui;

/**
 * Represents a command to unmark a task in the task list.
 */
public class UnmarkCommand extends Command {
    private final String remainder;

    /**
     * Constructs an UnmarkCommand object.
     *
     * @param remainder Index of the task to unmark.
     */
    public UnmarkCommand(String remainder) {
        super(false);
        this.remainder = remainder;
    }

    /**
     * Unmarks a task in the task list.
     *
     * @param list Task list to unmark task from.
     * @param ui Ui to display the unmarked task.
     * @return a message indicating the task has been unmarked.
     */
    @Override
    public String execute(TaskList list, Ui ui) {
        try {
            int index = Integer.parseInt(remainder) - 1;
            Task task = list.getTask(index);
            task.unmark();
            return ui.displayUnmarkTask(task);
        } catch (NumberFormatException e) {
            return "Please enter a positive integer after unmark.\n";
        } catch (IndexOutOfBoundsException e) {
            return ("Invalid index: "
                    + remainder
                    + "\n"
                    + "Please enter a number within 1 to number of current tasks.\n"
            );
        }
    }
}
