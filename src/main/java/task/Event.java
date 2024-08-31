package task;
import exception.InputFormatException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Event class represents a task that occurs during a specific time period.
 * It extends the Task class and includes the start and end times of the event.
 */
public class Event extends Task{

    private final LocalDateTime fromDate;
    private final LocalDateTime toDate;

    /**
     * Constructs an Event task with the specified description, start time, and end time.
     *
     * @param description The description of the event.
     * @param fromDate    The start date and time of the event.
     * @param toDate      The end date and time of the event.
     */
    public Event(String description, LocalDateTime fromDate, LocalDateTime toDate) {
        super(description);
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    /**
     * Returns a string representation of the event task in a format suitable for file storage.
     * The format is: "E | doneStatus | description | fromDate | toDate", where doneStatus is 1 if the task is done,
     * 0 otherwise, and fromDate and toDate are formatted as "yyyy-MM-dd HH:mm".
     *
     * @return A string representation of the event task for file storage.
     */
    public String toFileFormatString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return String.format("E | %s | %s | %s", super.toFileFormatString(), fromDate.format(formatter), toDate.format(formatter));
    }

    /**
     * Returns a string representation of the event task, including its status, description, start time, and end time.
     *
     * @return A string representation of the event task.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy h:ma");
        return String.format("[E] %s (from: %s to: %s)\n",super.toString(), fromDate.format(formatter), toDate.format(formatter));
    }



    public static boolean matchEvent(String s) {
        return s.startsWith("event");
    }
}
