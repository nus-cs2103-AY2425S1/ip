package knight2103.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Models a task to do that contains a start date & time and an end date & time.
 */
public class EventTask extends Task {
    protected final LocalDateTime startTime;
    protected final LocalDateTime endTime;
    private static final String EVENT_IDENTIFIER = "E";
    private static final String DATE_TIME_FORMAT_FOR_LIST = "d MMM (E) HH:mm";

    /**
     * Constructs a task object that is of an Event nature. It contains a description of the event task
     * and the information of the start date & time and end date & time.
     * The object by default has the completion status set as not done.
     *
     * @param description The description of the task.
     * @param startTime The start date & time of the task.
     * @param endTime The end date & time of the task.
     */
    public EventTask(String description, String startTime, String endTime) {
        super(description);
        this.startTime = LocalDateTime.parse(startTime);
        this.endTime = LocalDateTime.parse(endTime);
    }

    @Override
    public String toStringInFile() {
        return String.format("%s %s | %s | %s", EVENT_IDENTIFIER, super.toStringInFile(),
                this.startTime, this.endTime);
    }

    @Override
    public String toString() {
        return String.format("[%s]%s (from: %s to: %s)", EVENT_IDENTIFIER, super.toString(),
                this.startTime.format(DateTimeFormatter.ofPattern(DATE_TIME_FORMAT_FOR_LIST)),
                this.endTime.format(DateTimeFormatter.ofPattern(DATE_TIME_FORMAT_FOR_LIST)));
    }
}
