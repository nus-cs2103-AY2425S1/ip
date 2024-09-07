package lawrence.task;

import java.time.LocalDateTime;

import lawrence.utils.DateParser;

/**
 * Represents a task that has a start time and an end time.
 */
public class Event extends Task {
    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Constructor. Creates an {@link Event} object with the specified
     * task description, start time and end time.
     * <p>
     * The task will be marked as incomplete by default.
     * </p>
     *
     * @param description the name of the event
     * @param from the start date of the event
     * @param to the end date of the event
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Constructor. Creates an {@link Event} object with the specified
     * task description, completion status, start time and end time.
     * <p>
     * The task can be marked as complete or incomplete on creation.
     * </p>
     *
     * @param description the name of the event
     * @param isComplete indicates whether the event is complete
     * @param from the start date of the event
     * @param to the end date of the event
     */
    public Event(String description, boolean isComplete, LocalDateTime from, LocalDateTime to) {
        super(description, isComplete);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a string representation of the object in a
     * standardised save format.
     *
     * @return a string representation of the object in save format
     */
    public String toSaveFormat() {
        return String.format("E | %s | %s | %s",
                super.toSaveFormat(),
                DateParser.toOutputString(from),
                DateParser.toOutputString(to));
    }

    /**
     * Returns a string representation of the object.
     *
     * @return a string representation of the object
     */
    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(),
                DateParser.toOutputString(from),
                DateParser.toOutputString(to));
    }
}
