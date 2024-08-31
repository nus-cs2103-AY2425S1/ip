package phenex.command;

import phenex.exception.PhenexException;
import phenex.storage.Storage;
import phenex.task.Event;
import phenex.task.TaskList;
import phenex.ui.Ui;

import java.time.LocalDate;

public class EventCommand extends CreateTaskCommand {
    private LocalDate fromDate;
    private LocalDate toDate;

    public EventCommand() {
        super("");
    }

    public EventCommand(String name, LocalDate from, LocalDate to) {
        super(name);
        this.fromDate = from;
        this.toDate = to;
    }

    public void setDates(LocalDate from, LocalDate to) throws PhenexException {
        if (to.isBefore(from)) {
            throw new PhenexException("Error: invalid event dates");
        }
        this.fromDate = from;
        this.toDate = to;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws PhenexException {
        Event event = new Event(this.name, this.fromDate, this.toDate);
        taskList.addTask(event);
        ui.printTaskAddedMessage(event, taskList.getTasks().size());
    }
}
