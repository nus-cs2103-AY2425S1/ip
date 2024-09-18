package tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an event task that occurs over a specific time period.
 * An EventTask has a description, start and end dates, and an optional note.
 */
public class EventTask extends Task {
    private static final String SYMBOL = "E";
    private String from;
    private String to;
    private LocalDate fromDate;
    private LocalDate toDate;

    /**
     * Constructs an EventTask with a description, a start date, and an end date.
     *
     * @param description The description of the event task.
     * @param from The start date of the event in the format YYYY-MM-DD.
     * @param to The end date of the event in the format YYYY-MM-DD.
     * @throws DateTimeParseException If the from or to dates are not in the correct format.
     */
    public EventTask(String description, String from, String to) throws DateTimeParseException {
        super(description);
        this.fromDate = LocalDate.parse(from);
        this.toDate = LocalDate.parse(to);
    }

    /**
     * Constructs an EventTask with a description, a start date, an end date, and a note.
     *
     * @param description The description of the event task.
     * @param from The start date of the event in the format YYYY-MM-DD.
     * @param to The end date of the event in the format YYYY-MM-DD.
     * @param note An optional note associated with the event task.
     * @throws DateTimeParseException If the from or to dates are not in the correct format.
     */
    public EventTask(String description, String from, String to, String note) throws DateTimeParseException {
        super(description, note);
        this.fromDate = LocalDate.parse(from);
        this.toDate = LocalDate.parse(to);
    }

    /**
     * Gets the symbol representing the type of task.
     *
     * @return A string symbol representing this task type ("E" for EventTask).
     */
    public String getSymbol() {
        return SYMBOL;
    }

    /**
     * Gets a string representation of the timings associated with the event task.
     *
     * @return A string indicating the start and end dates of the event.
     */
    public String getTimings() {
        return "(from: " + this.fromDate + " to: " + this.toDate + ")";
    }

    /**
     * Returns a string representation of the EventTask, including its status,
     * description, start and end dates, and any associated note.
     *
     * @return A formatted string representing the EventTask.
     */
    @Override
    public String toString() {
        String taskString = String.format("[%s][%s] %s (from: %s to: %s)", this.SYMBOL, super.getStatusIcon(),
                super.description, this.fromDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")),
                this.toDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
        taskString += "\nNote: " + super.note + "\n";
        return taskString;
    }
}
