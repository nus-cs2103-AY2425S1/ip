package friday.command;

import java.time.LocalDate;

import friday.util.Storage;
import friday.util.TaskList;
import friday.util.Ui;

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
        assert date != null : "Date should not be null";
        this.date = date;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        assert tasks != null : "TaskList should not be null";
        assert ui != null : "Ui should not be null";
        assert storage != null : "Storage should not be null";

        TaskList specificTasks = tasks.findTasksByDate(date);
        return ui.showSpecificTasks(specificTasks, date);
    }

    @Override
    public boolean shouldExit() {
        return false;
    }
}
