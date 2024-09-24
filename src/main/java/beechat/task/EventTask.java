package beechat.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task in the Beechat chatbot application.
 * An event task is a task happens from a specific date and time to a specified date and time.
 */
public class EventTask extends Task {
    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Constructs an EventTask with the specified description and start and end dates and times.
     *
     * @param description The description of the task.
     * @param from The start date and time for the task.
     * @param to The end date and time for the task.
     */
    public EventTask(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the string representation of the event task for saving to a file.
     *
     * @return A formatted string representation of the task in a file-friendly format.
     */
    @Override
    public String toSaveFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | "
                + from.format(formatter) + " | " + to.format(formatter);
    }

    /**
     * Returns the string representation of the event task with its start and ned dates and times.
     *
     * @return A formatted string representation of the task.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");
        return "[E]" + super.toString() + "(from: " + from.format(formatter)
                + " to: " + to.format(formatter) + ")";
    }
}
