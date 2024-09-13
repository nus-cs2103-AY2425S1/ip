package demurebot.command;

import demurebot.task.Task;
import demurebot.task.TaskList;
import demurebot.ui.Ui;

/**
 * Represents a command to unmark a task in the task list.
 */
public class UnmarkCommand extends Command {
    private final String index;

    /**
     * Constructs an UnmarkCommand object.
     *
     * @param index Index of the task to unmark.
     */
    public UnmarkCommand(String index) {
        this.index = index;
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
            int i = Integer.parseInt(index) - 1;
            Task task = list.getTask(i);
            task.unmark();
            return ui.displayUnmarkTask(task);
        } catch (NumberFormatException e) {
            return "Please enter a positive integer after unmark.\n";
        } catch (IndexOutOfBoundsException e) {
            return ("Invalid index: "
                    + index
                    + "\n"
                    + "Please enter a number within 1 to number of current tasks.\n"
            );
        }
    }
}
