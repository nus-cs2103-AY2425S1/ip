package phenex.command;

import java.time.LocalDate;

import phenex.exception.PhenexException;
import phenex.storage.Storage;
import phenex.task.TaskList;
import phenex.ui.Ui;

/**
 * ScheduleCommand class which encapsulates a Command which lists all tasks scheduled on a certain day.
 */
public class ScheduleCommand extends Command {

    private LocalDate fromDate;
    private LocalDate toDate;

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws PhenexException {
        // implement find all tasks within date range
        return ui.printTaskList(taskList.findAllTasksBetween(fromDate, toDate));
    }

    @Override
    public boolean isTerminatingCommand() {
        return false;
    }

    public void setScheduleDates(LocalDate fromDate, LocalDate toDate) throws PhenexException {
        if (toDate.isBefore(fromDate)) {
            throw new PhenexException("Error: invalid event dates");
        }
        this.fromDate = fromDate;
        this.toDate = toDate;
    }
}
