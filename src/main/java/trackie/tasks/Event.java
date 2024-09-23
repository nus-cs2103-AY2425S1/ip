package trackie.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * Represents a task with type "Event".
 */
public class Event extends Task {
    private String type = "E";
    private String dateTimePattern = "yyyyMMdd HHmm";

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    private DateTimeFormatter parsingFormatter = DateTimeFormatter.ofPattern(dateTimePattern);
    private DateTimeFormatter readingFormatter = DateTimeFormatter.ofLocalizedDateTime(
            FormatStyle.MEDIUM,
            FormatStyle.MEDIUM
    );

    /**
     * Constructor to create an event task.
     *
     * @param description the description of the task.
     * @param startString the start time of the task, as a string.
     * @param endString the end time of the task, as a string.
     */
    public Event(String description, String startString, String endString) {
        super(description);
        this.startTime = LocalDateTime.parse(startString, parsingFormatter);
        this.endTime = LocalDateTime.parse(endString, parsingFormatter);
    }

    /**
     * Creates an event task with a custom completion status.
     *
     * @param description the description of the task.
     * @param startString the start time of the task.
     * @param endString the end time of the task.
     * @param status the completion of the status of the task.
     */
    public Event(String description, String startString, String endString, int status) {
        super(description, status);
        this.startTime = LocalDateTime.parse(startString, parsingFormatter);
        this.endTime = LocalDateTime.parse(endString, parsingFormatter);
    }

    /**
     * Retrieves relevant information about the task.
     *
     * @return A String containing the description, the start time and the end time of the task.
     */
    public String toString() {
        return(String.format("%s, (from: %s to: %s)", super.description,
                this.startTime.format(readingFormatter),
                this.endTime.format(readingFormatter)));
    }

    /**
     * Retrieves the type of the task.
     * @return A String denoting the type of the task. In this case, the type is "E".
     */
    public String getTaskType() {
        return(this.type);
    }

    /**
     * Retrieves the start time of the task.
     *
     * @return A String denoting the start time of the task.
     */
    public String getStartTime() { return this.startTime.format(parsingFormatter); }

    /**
     * Retrieves the end time of the task.
     *
     * @return A String denoting the end time of the task.
     */
    public String getEndTime() { return this.endTime.format(parsingFormatter); }
}
