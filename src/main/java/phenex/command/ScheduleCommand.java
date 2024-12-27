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

    private LocalDate scheduleDate;

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws PhenexException {
        return ui.printTaskList(taskList.findAllTasksOn(scheduleDate));
    }

    @Override
    public boolean isTerminatingCommand() {
        return false;
    }

    public void setScheduleDate(LocalDate scheduleDate) {
        this.scheduleDate = scheduleDate;
    }
}
