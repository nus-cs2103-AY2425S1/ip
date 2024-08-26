package friday.command;

import friday.Storage;
import friday.TaskList;
import friday.Ui;

import java.time.LocalDate;

/**
 * Represents a command to list tasks occurring on a specific date.
 * Inherits from the Command class and provides functionality to filter and display tasks based on a given date.
 */
public class OnCommand extends Command {
    private LocalDate date;

    /**
     * Constructs an OnCommand with the specified date.
     *
     * @param date The date for which tasks need to be listed.
     */
    public OnCommand(LocalDate date) {
        this.date = date;
    }

    /**
     * Executes the command to display tasks occurring on the specified date.
     * This method retrieves tasks for the given date and displays them using the Ui object.
     *
     * @param tasks  The TaskList containing all tasks where tasks matching the date will be filtered.
     * @param ui     The Ui object for displaying the tasks to the user.
     * @param storage The Storage object (not used in this command but included for method signature consistency).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showSpecificTasks(tasks, date);
    }

    /**
     * Indicates whether the command is an exit command.
     *
     * @return false as this command does not terminate the application.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}