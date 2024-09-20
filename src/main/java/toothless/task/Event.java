package toothless.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import toothless.exceptions.InvalidTimelineException;
import toothless.exceptions.ToothlessExceptions;

/**
 * Represents an event task.
 */
public class Event extends Task {

    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy[ ]HHmm");
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");
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
        assert eventStart != null : "Event start should not be null";
        assert eventEnd != null : "Event end should not be null";
        try {
            LocalDateTime startTimeline = LocalDateTime.parse(eventStart.trim().replace("-", "/"),
                    INPUT_FORMATTER);
            LocalDateTime endTimeline = LocalDateTime.parse(eventEnd.trim().replace("-", "/"),
                    INPUT_FORMATTER);

            if (startTimeline.isBefore(LocalDateTime.now()) || endTimeline.isBefore(LocalDateTime.now())) {
                throw new InvalidTimelineException("the event start or end date and time is earlier than today! "
                        + "Please enter a valid date and time.\n");
            } else if (startTimeline.isAfter(endTimeline)) {
                throw new InvalidTimelineException("the start date and time should be before the end date and time.\n");
            }
            this.eventStart = startTimeline;
            this.eventEnd = endTimeline;
        } catch (DateTimeParseException e) {
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
        assert eventStart != null : "Event start should not be null";
        assert eventEnd != null : "Event end should not be null";
        try {
            LocalDateTime startTimeline = LocalDateTime.parse(eventStart.trim().replace("-", "/"),
                    INPUT_FORMATTER);
            LocalDateTime endTimeline = LocalDateTime.parse(eventEnd.trim().replace("-", "/"),
                    INPUT_FORMATTER);

            if (startTimeline.isBefore(LocalDateTime.now()) || endTimeline.isBefore(LocalDateTime.now())) {
                throw new InvalidTimelineException("the event start or end date and time is earlier than today! "
                        + "Please enter a valid date and time.\n");
            } else if (startTimeline.isAfter(endTimeline)) {
                throw new InvalidTimelineException("the start date and time should be before the end date and time.\n");
            }
            this.eventStart = startTimeline;
            this.eventEnd = endTimeline;
        } catch (DateTimeParseException e) {
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
                + eventStart.format(OUTPUT_FORMATTER) + " | " + eventEnd.format(OUTPUT_FORMATTER);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + eventStart.format(OUTPUT_FORMATTER)
                + " to: " + eventEnd.format(OUTPUT_FORMATTER) + ")";
    }
}
