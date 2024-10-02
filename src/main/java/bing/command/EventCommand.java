package bing.command;

import bing.task.Event;
import java.time.LocalDateTime;

/**
 * Represents a command to add an Event task.
 */
public class EventCommand extends AddCommand {

    /**
     * Constructs an EventCommand with the specified information, start time, and end time.
     *
     * @param info The description of the event.
     * @param from The start time of the event.
     * @param to The end time of the event.
     */
    public EventCommand(String info, LocalDateTime from, LocalDateTime to) {
        super(new Event(info, from, to));
    }
}
