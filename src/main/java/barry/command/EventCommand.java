package barry.command;

import barry.task.Event;

/**
 * Represents a command that adds an event task to the task list.
 */
public class EventCommand extends AddCommand {

    /**
     * Constructs an EventCommand with the specified description, start time and end time.
     *
     * @param description The description of the event task.
     * @param from The start time of the event.
     * @param to The end time of the event.
     */
    public EventCommand(String description, String from, String to) {
        super(new Event(description, from, to));
    }
}
