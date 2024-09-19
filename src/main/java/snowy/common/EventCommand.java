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
    private final String description;
    private final String from;
    private final String to;

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
            String str = taskList.addTask(event);
            return new CommandResult(str + "\nAdded an event to your list of tasks");
        } catch (SnowyException e) {
            return new CommandResult("An error occurred while adding the event: " + e.getMessage());
        }
    }
}
