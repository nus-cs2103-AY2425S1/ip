package phenex.command;

import java.time.LocalDate;

import phenex.exception.PhenexException;
import phenex.storage.Storage;
import phenex.task.Event;
import phenex.task.TaskList;
import phenex.ui.Ui;

/**
 * EventCommand class which encapsulates a Command which creates an Event task.
 */
public class EventCommand extends CreateTaskCommand {
    /** encapsulates the from and to dates of the event */
    private LocalDate fromDate;
    private LocalDate toDate;

    public EventCommand() {
        super("");
    }

    /**
     * Creates an EventCommand object which creates an Event object.
     * @param name the name of the EventCommand
     * @param from the from date of the event to be created.
     * @param to the to date of the event to be created.
     */
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
    public String execute(TaskList taskList, Ui ui, Storage storage) throws PhenexException {
        Event event = new Event(this.name, this.fromDate, this.toDate);
        if (taskList.containsTask(event)) {
            throw new PhenexException("Error: Duplicate Mission. Aborting!");
        }
        taskList.addTask(event);
        return ui.printTaskAddedMessage(event, taskList.getTasks().size());
    }
}
