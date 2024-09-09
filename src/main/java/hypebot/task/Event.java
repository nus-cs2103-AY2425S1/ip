package hypebot.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static hypebot.common.Messages.EVENT_TIME_PARSE_ERROR;

/**
 * Represents an Event type Task with a LocalDateTime type start time and an end time.
 *
 * @author Youngseo Park (@youngseopark05)
 */
public class Event extends Task {
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    /**
     * Creates an Event task with the specified name, start time, and end time.
     * If due date entered by user does not follow specific format, throws DateTimeException.
     *
     * @param name The name of the event.
     * @param startTimeString The start time of the event.
     * @param endTimeString The end time of the event.
     * @throws EventTimeDateParseException Thrown if due date entered by user does not follow format 'yyyy-MM-dd HH:mm'.
     */
    public Event(String name, String startTimeString, String endTimeString) throws EventTimeDateParseException {
        super(name);
        try {
            startTime = LocalDateTime.parse(startTimeString, formatter);
            endTime = LocalDateTime.parse(endTimeString, formatter);
        } catch (DateTimeParseException e) {
            throw new EventTimeDateParseException(EVENT_TIME_PARSE_ERROR, e.getParsedString(), e.getErrorIndex());
        }
    }

    @Override
    public boolean isHappeningOn(LocalDate date) {
        LocalDate startDate = startTime.toLocalDate();
        LocalDate endDate = endTime.toLocalDate();
        return (date.isEqual(startDate) || date.isAfter(startDate))
                && (date.isEqual(endDate) || date.isBefore(endDate));
    }

    /**
     * Returns the String description of the task to append to /data/tasklist.txt.
     * Should be in this form: "E , {0 if not complete, 1 if complete} , {name} , {startTime} , {endTime}".
     *
     * @return String description of Event task to append to /data/tasklist.txt.
     */
    @Override
    public String toFileString() {
        return "E , " + (isComplete() ? 1 : 0) + " , " + getName() + " , "
                + startTime.format(formatter) + " , " + endTime.format(formatter) + "\n";
    }

    /**
     * Returns the String representation of the Deadline task as shown to the user on the HypeBot UI.
     * Should be in this form: "[E][{X only if complete}] {name} (from: {startTime} to: {endTime})".
     *
     * @return String representation of Event task as shown on HypeBot UI.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: "
                + startTime.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")) + " to: "
                + endTime.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")) + ")";
    }
}
