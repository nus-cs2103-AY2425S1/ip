package phenex.command;

import phenex.exception.PhenexException;
import phenex.storage.Storage;
import phenex.task.TaskList;
import phenex.ui.Ui;

import java.time.LocalDate;

public class DateCheckCommand extends Command {
    /** encapsulates the date to check */
    private LocalDate localDate;

    public DateCheckCommand() {
        this.localDate = LocalDate.parse("2020-01-01");
    }

    public DateCheckCommand(LocalDate localDate) {
        this.localDate = localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws PhenexException {
        TaskList taskListWithDates = taskList.findAllTasksOn(this.localDate);
        ui.printTaskList(taskListWithDates);
    }
}
