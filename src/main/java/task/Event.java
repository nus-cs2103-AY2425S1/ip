package task;

import java.time.LocalDate;

/**
 * Represents an event with a start and end date.
 * <p>
 * The {@code Event} class extends {@link Task} to include both a start date and
 * an end date. It provides methods to represent the event with its dates as a
 * string and to retrieve a formatted string suitable for database storage.
 * </p>
 */
public class Event extends Task {
    protected LocalDate start;
    protected LocalDate end;

    /**
     * Constructs an {@code Event} with the specified description, start date, and end date.
     * <p>
     * This constructor initializes the event with the given description, start date,
     * and end date. Both dates are parsed from strings into {@link LocalDate} objects.
     * </p>
     *
     * @param description The description of the event.
     * @param start       The start date of the event in string format (e.g., "2024-09-01").
     * @param end         The end date of the event in string format (e.g., "2024-09-02").
     */
    public Event(String description, String start, String end) {
        super(description);
        this.start = LocalDate.parse(start);
        this.end = LocalDate.parse(end);
    }

    /**
     * Constructs an {@code Event} with the specified description, start date, end date,
     * and completion status.
     * <p>
     * This constructor initializes the event with the given description, start date,
     * end date, and whether the event is completed. Both dates are parsed from strings
     * into {@link LocalDate} objects.
     * </p>
     *
     * @param description The description of the event.
     * @param start       The start date of the event in string format (e.g., "2024-09-01").
     * @param end         The end date of the event in string format (e.g., "2024-09-02").
     * @param isDone      A boolean indicating whether the event is completed.
     */
    public Event(String description, String start, String end, boolean isDone) {
        super(description, isDone);
        this.start = LocalDate.parse(start);
        this.end = LocalDate.parse(end);
    }

    /**
     * Returns a string representation of the {@code Event} for display purposes.
     * <p>
     * The string format is "[E][task details] (from: [start date] to: [end date])".
     * The start and end dates are formatted for display purposes.
     * </p>
     *
     * @return A string representation of the {@code Event} for display.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + getDateStringPrintFormat(this.start) + " to: "
                + getDateStringPrintFormat(this.end) + ")";
    }

    /**
     * Returns a string representation of the {@code Event} suitable for database storage.
     * <p>
     * The string format is "E | [isDone] | [description] | [start date] | [end date]".
     * The start and end dates are formatted for storage purposes.
     * </p>
     *
     * @return A string representation of the {@code Event} suitable for database storage.
     */
    @Override
    public String getDatabaseString() {
        return String.format(
                "E | %d | %s | %s | %s",
                this.isDone ? 1 : 0,
                this.description,
                getDateStringStorageFormat(this.start),
                getDateStringStorageFormat(this.start)
        );
    }
}