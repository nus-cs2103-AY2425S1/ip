package evan.command;

import evan.service.TaskList;
import evan.task.Event;

/**
 * Represents a command that the chatbot can execute to create an Event.
 */
public class EventCommand extends Command {
    private final String description;
    private final String from;
    private final String to;

    /**
     * Instantiates an EventCommand object.
     *
     * @param description Description of the Event to be created.
     * @param from        Start date/time of the Event to be created.
     * @param to          End date/time of the Event to be created.
     */
    public EventCommand(String description, String from, String to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList taskList) {
        Event event = new Event(description, from, to);
        taskList.add(event);
        return "Got it. I've added this event:\n" + event;
    }
}
