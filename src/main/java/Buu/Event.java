package Buu;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task with a start time and an end time in the GPT application.
 * This class extends the Task class to include specific start and end times for the event.
 */
class Event extends Task {
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma");
    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Constructs an Event task with the specified description, start time, and end time.
     *
     * @param description The description of the event.
     * @param from        The start time of the event.
     * @param to          The end time of the event.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description, TaskType.EVENT);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a string representation of the event task, including the start and end times.
     *
     * @return A string representation of the event task.
     */
    @Override
    public String toString() {
        if (from != null && to != null) {
            return super.toString() + " (from: " + from.format(OUTPUT_FORMATTER) + " to: "
                    + to.format(OUTPUT_FORMATTER) + ")";
        } else {
            return super.toString() + " (from: [Invalid Date] to: [Invalid Date])";
        }
    }

    /**
     * Returns a string format suitable for saving the event task to a file.
     *
     * @return A string representation of the event task in a format suitable for file storage.
     */
    @Override
    public String toFileFormat() {
        if (from != null && to != null) {
            return String.format("E | %d | %s | %s | %s",
                    isDone ? 1 : 0,
                    description,
                    from.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")),
                    to.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")));
        } else {
            return String.format("E | %d | %s | [Invalid Date] | [Invalid Date]",
                    isDone ? 1 : 0,
                    description);
        }
    }
}