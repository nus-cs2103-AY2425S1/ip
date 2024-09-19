package barcus.command;

import java.time.format.DateTimeParseException;

import barcus.exception.BarcusException;
import barcus.storage.Storage;
import barcus.task.Event;
import barcus.task.Task;
import barcus.tasklist.TaskList;

/**
 * Command to add new event task
 */
public class AddEventCommand extends AddCommand {
    protected String from;
    protected String to;

    /**
     * Constructs an AddEventCommand with the specified description, start time, and end time.
     *
     * @param description the description of the event
     * @param from the start time of the event in the format dd/MM/yyyy HH:mm
     * @param to the end time of the event in the format dd/MM/yyyy HH:mm
     */
    public AddEventCommand(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Executes the command by adding an event task to the task list.
     *
     * @param tasks the task list to add the event task to
     * @param storage the storage object to save the task
     * @throws BarcusException if the date format is invalid
     */
    @Override
    public void execute(TaskList tasks, Storage storage) throws BarcusException {
        try {
            Task t = new Event(this.description, this.from, this.to);
            tasks.addTask(t);
            output = "Added task: " + t + "\nThere are " + tasks.getLength() + " task(s) in the list.";
        } catch (DateTimeParseException e) {
            throw new BarcusException("please format date as dd/MM/yyyy HH:mm");
        }

    }

    /**
     * Returns whether this command causes the application to exit.
     *
     * @return false, as adding an event does not cause the application to exit
     */
    @Override
    public boolean isExit() {
        return false;
    }

}
