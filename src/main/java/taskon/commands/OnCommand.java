package taskon.commands;

import taskon.storage.Storage;
import taskon.task.TaskList;
import taskon.ui.Ui;

/**
 * Represents a command to display tasks scheduled on a specific date.
 * <p>
 * This class handles filtering and displaying tasks in the task list that
 * are scheduled on a specified date.
 * </p>
 */
public class OnCommand extends Command {
    public static final String COMMAND_WORD = "on";
    private String date;

    /**
     * Constructs an {@code OnCommand} with the specified date.
     *
     * @param date The date for which tasks should be displayed.
     */
    public OnCommand(String date) {
        this.date = date;
    }


    /**
     * Executes the command to display tasks scheduled on a specific date.
     * <p>
     * This method filters the task list to find tasks scheduled on the specified
     * date and updates the user interface to display them.
     * </p>
     *
     * @param taskList The list of tasks to be filtered by date.
     * @param ui       The user interface that handles the display of the filtered tasks.
     * @param storage  The storage (not used in this command).
     * @return A string message that displays a list of task that occurs on a specified date.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.showTasksOnDate(date, taskList);
    }
}
