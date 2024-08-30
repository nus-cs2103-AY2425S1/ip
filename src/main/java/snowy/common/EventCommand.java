package snowy.common;

import snowy.data.SnowyException;
import snowy.tasklist.Event;

public class EventCommand extends Command {
    public static final String EVENT = "event";
    private final String description;
    private final String from;
    private final String to;

    public EventCommand(String description, String from, String to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

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
