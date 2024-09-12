package garfield.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * The Event class represents a task with a specific time period in the Garfield chatbot application.
 * It extends the Task class to provide a representation for tasks that have a start and end time.
 */
public class Event extends Task {
    private LocalDateTime from;
    private LocalDateTime to;
    private DateTimeFormatter saveFormatter;
    private DateTimeFormatter uiFormatter;

    /**
     * Constructs a new Event task with the specified description, start time, and end time.
     *
     * @param eventDescription The description of the event task.
     * @param from The start time of the event, represented as a LocalDateTime object.
     * @param to The end time of the event, represented as a LocalDateTime object.
     */
    public Event(String eventDescription, LocalDateTime from, LocalDateTime to) {
        super(eventDescription);
        this.from = from;
        this.to = to;
        this.saveFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm", Locale.US);
        this.uiFormatter = DateTimeFormatter.ofPattern("MMM d yyyy hh:mma", Locale.US);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from.format(uiFormatter) + " to: "
                + this.to.format(uiFormatter) + ")";
    }

    @Override
    public String toSaveRepresentation() {
        return "E | " + super.toSaveRepresentation() + " | " + this.from.format(saveFormatter) + " | "
                + this.to.format(saveFormatter);
    }
}
