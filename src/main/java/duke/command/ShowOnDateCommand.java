package duke.command;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a command to show tasks scheduled on a specific date in the task list.
 */
public class ShowOnDateCommand implements Command {
    private LocalDate date;

    /**
     * Constructs a ShowOnDateCommand with the specified date.
     *
     * @param date The date for which tasks will be shown.
     */
    public ShowOnDateCommand(LocalDate date) {
        this.date = date;
    }

    /**
     * Executes the show-on-date command, retrieving tasks scheduled on the specified date
     * and displaying them to the user.
     *
     * @param tasks The task list containing all tasks.
     * @param ui The user interface to interact with the user.
     * @param storage The storage system of the application (not used in this command).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        List<Task> tasksOnDate = tasks.getTasksOnDate(date);
        ui.showTasksOnDate(tasksOnDate, date);
    }

    /**
     * Returns whether this command will terminate the application.
     *
     * @return false, as the show-on-date command does not terminate the application.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}