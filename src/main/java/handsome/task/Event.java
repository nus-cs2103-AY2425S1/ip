package handsome.task;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import handsome.exception.InvalidTaskException;

/**
 * The Event class represents a task with a specific start and end time
 * in the Handsome chatBot application. It extends the Task class and adds
 * start and end times to the task description.
 */
public class Event extends Task {
    private final String startTime;
    private final String endTime;

    /**
     * Constructs an Event object with the specified content, status, start time, and end time.
     * If the isFormatted parameter is false, the start and end times are formatted using the
     * formatDate method. If the start time is after the end time, an exception is thrown.
     *
     * @param content     The description of the event task.
     * @param isDone      The status of the task, either marked as done or not done.
     * @param startTime   The start time of the event as a string.
     * @param endTime     The end time of the event as a string.
     * @param isFormatted A boolean indicating whether the start and end times are already formatted.
     * @throws InvalidTaskException If the date and time are incorrectly formatted or if the end time
     *                              is earlier than the start time.
     */
    public Event(String content, String isDone, String startTime, String endTime, boolean isFormatted)
            throws InvalidTaskException {
        super(content, isDone);
        if (isFormatted) {
            this.startTime = startTime;
            this.endTime = endTime;
        } else {
            try {
                this.startTime = formatDate(startTime);
                this.endTime = formatDate(endTime);
                if (LocalDateTime.parse(startTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"))
                        .isAfter(LocalDateTime.parse(endTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")))) {
                    throw new InvalidTaskException("End time of event must be later than start time!");
                }
            } catch (DateTimeException dateTimeException) {
                throw new InvalidTaskException("Date and time must be formatted correctly for event! Type prompt to "
                        + "check the correct date format");
            }
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + startTime + " to: " + endTime + ")";
    }

    @Override
    public String toDataFormat() {
        return "E | " + super.toDataFormat() + " | " + startTime + " | " + endTime;
    }
}
