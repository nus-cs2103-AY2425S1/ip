package snowy.common;

import snowy.data.SnowyException;
import snowy.tasklist.Event;

/**
 * Represents a command to add an event to the task list.
 *
 * The EventCommand class allows the user to add an event task, which has a description,
 * a start time ("from"), and an end time ("to"). When executed, it creates an Event task
 * and adds it to the task list. The command returns a result indicating that the event
 * was added, or an error message if the event could not be created.
 */
public class EventCommand extends Command {
    public static final String EVENT = "event";
    private final String description;
    private final String from;
    private final String to;

    /**
     * Constructs an EventCommand with the specified description, start time, and end time.
     *
     * @param description the description of the event
     * @param from the start time of the event in the format "yyyy-MM-dd HHmm"
     * @param to the end time of the event in the format "yyyy-MM-dd HHmm"
     */
    public EventCommand(String description, String from, String to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    /**
     * Executes the command to add an event task to the task list.
     *
     * @return a CommandResult indicating the event has been added, or an error message if it fails
     */
    @Override
    public CommandResult execute() {
        try {
            Event event = new Event(description, from, to);
            taskList.addTask(event);
            return new CommandResult("Added an event to your list of tasks");
        } catch (SnowyException e) {
            return new CommandResult("An error occurred while adding the event: " + e.getMessage());
        }
    }
}
