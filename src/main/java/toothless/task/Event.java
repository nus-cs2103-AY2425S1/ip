package toothless.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import toothless.exceptions.ToothlessExceptions;

/**
 * Represents an event task.
 */
public class Event extends Task {

    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy[ ]HHmm");
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
    protected LocalDateTime eventStart;
    protected LocalDateTime eventEnd;

    /**
     * Constructor for Events.
     *
     * @param description the description of the event
     * @param eventStart  the start date and time of the event
     * @param eventEnd    the end date and time of the event
     * @throws ToothlessExceptions if the date and time format is invalid
     */
    public Event(String description, String eventStart, String eventEnd) throws ToothlessExceptions {
        super(description);
        try {
            this.eventStart = LocalDateTime.parse(eventStart.trim().replace("-", "/"), INPUT_FORMATTER);
            this.eventEnd = LocalDateTime.parse(eventEnd.trim().replace("-", "/"), INPUT_FORMATTER);
        } catch (Exception e) {
            throw new ToothlessExceptions("Please enter a valid date and time\n"
                    + "in the format: dd/MM/yyyy HHmm or dd-MM-yyyy HHmm\n");
        }
    }

    /**
     * Constructor for Events.
     *
     * @param description the description of the event
     * @param eventStart the start date and time of the event
     * @param eventEnd the end date and time of the event
     * @param isDone the status of the event
     * @throws ToothlessExceptions if the date and time format is invalid
     */
    public Event(String description, String eventStart, String eventEnd, boolean isDone) throws ToothlessExceptions {
        super(description, isDone);
        try {
            this.eventStart = LocalDateTime.parse(eventStart.trim().replace("-", "/"), INPUT_FORMATTER);
            this.eventEnd = LocalDateTime.parse(eventEnd.trim().replace("-", "/"), INPUT_FORMATTER);
        } catch (Exception e) {
            throw new ToothlessExceptions("Please enter a valid date and time\n"
                    + "in the format: dd/MM/yyyy HHmm or dd-MM-yyyy HHmm\n");
        }
    }

    /**
     * Returns the string representation of the event task.
     *
     * @return the string representation of the event task.
     */
    @Override
    public String toFileString() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | "
                + eventStart.format(INPUT_FORMATTER) + " | " + eventEnd.format(INPUT_FORMATTER);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + eventStart.format(OUTPUT_FORMATTER)
                + " to: " + eventEnd.format(OUTPUT_FORMATTER) + ")";
    }
}
