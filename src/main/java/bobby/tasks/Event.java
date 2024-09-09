package bobby.tasks;

import bobby.exception.EmptyDescriptionException;

/**
 * Event is a type of task.
 * It represents a task with a description, a starting and ending period.
 */
public class Event extends Task {
    private final String start;
    private final String end;

    /**
     * Constructs an Event task with a description, start period and end period.
     *
     * @param description Description of the task.
     * @param start Start period of the task.
     * @param end End period of the task.
     */
    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /**
     * Creates an event task from a string input given by the user.
     *
     * @param input Command given by the user.
     * @return The Event task created from the input.
     * @throws EmptyDescriptionException If the user did not provide any description.
     */
    public static Event createEvent(String input) throws EmptyDescriptionException {
        String eventDescription = input.substring(5).trim();
        if (eventDescription.isEmpty()) {
            throw new EmptyDescriptionException("event");
        }
        String eventName = eventDescription.split(" /")[0];
        String eventStart = eventDescription.split(" /")[1].substring(5);
        String eventEnd = eventDescription.split(" /")[2].substring(3);
        return new Event(eventName, eventStart, eventEnd);
    }

    /**
     * @inheritDoc
     */
    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)" , super.toString(), this.start, this.end);
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getTaskType() {
        return "E";
    }

    /**
     * @inheritDoc
     */
    @Override
    public String taskInFile() {
        return String.format("E | %s | %s | /from %s /to %s", this.getStatusIcon(), this.getDescription(),
                this.start, this.end);
    }
}
