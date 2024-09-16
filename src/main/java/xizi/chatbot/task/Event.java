package xizi.chatbot.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task that occurs within a specific time frame.
 * This class extends the {@link Task} class and includes start and end times for the event.
 */
public class Event extends Task {
    private static final DateTimeFormatter OUTPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy, h:mma");
    protected LocalDateTime from;
    protected LocalDateTime to;
    /**
     * Constructs an {@code Event} task with the specified description, start time, and end time.
     *
     * @param description The description of the event.
     * @param from        The start time of the event.
     * @param to          The end time of the event.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public LocalDateTime getFrom() {
        return this.from;
    }

    public LocalDateTime getTo() {
        return this.to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: "
                + from.format(OUTPUT_DATE_FORMAT) + " to: " + to.format(OUTPUT_DATE_FORMAT) + ")"
                + (this.getTags().isEmpty() ? "" : " [Tags: " + getTags() + "]");
    }

    /**
     * Returns the string representation of the {@code Event} task in the format used for data file storage.
     * The format includes the task type, status, description, start time, and end time.
     *
     * @return A string in the file format to save the task.
     */
    @Override
    public String toFileFormat() {
        String fromDate = this.from.format(OUTPUT_DATE_FORMAT);
        String toDate = this.to.format(OUTPUT_DATE_FORMAT);
        String tags = this.getTags().isEmpty() ? "" : String.join(", ", this.getTags());
        return String.format("E | %d | %s | %s | %s | %s",
                this.isDone ? 1 : 0,
                this.name, fromDate, toDate, tags);
    }


}
