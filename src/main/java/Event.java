import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event task with a description, start time, and end time.
 */
public class Event extends Task {
    private LocalDateTime start;
    private LocalDateTime end;

    /**
     * Constructs an Event task with the specified description, start time, and end time.
     *
     * @param description The description of the Event task.
     * @param start The start time of the Event.
     * @param end The end time of the Event.
     */
    public Event(String description, LocalDateTime start, LocalDateTime end) {
        super(description, TaskType.EVENT);
        this.start = start;
        this.end = end;
    }

    @Override
    public void validateDescription(String command) throws OllieException {
        String[] parts = command.split(" /from | /to ");
        if (parts.length != 3) {
            throw new OllieException("Please enter the name, start time, and end time for the task! ☺");
        }
        if (parts[0].trim().isEmpty()) {
            throw new EmptyDescriptionException("event");
        }
        if (parts[1].trim().isEmpty()) {
            throw new OllieException("Please enter a start time for the task! ☺");
        }
        if (parts[2].trim().isEmpty()) {
            throw new OllieException("Please enter an end time for the task! ☺");
        }
    }

    public static Event createTask(String command) throws OllieException {
        String[] parts = command.substring(5).split(" /from:| /to:");
        if (parts.length != 3) {
            throw new OllieException("Please enter in the format:\n" +
                    "event event_name /from: start_time /to: end_time" +
                    "\nExample: event meeting /from: 2021-09-30 14:00 /to: 2021-09-30 15:00");
        }
        DateTimeFormatter inputDate = getInputDate();
        try {
            return new Event(parts[0].trim(), LocalDateTime.parse(parts[1].trim(), inputDate),
                    LocalDateTime.parse(parts[2].trim(), inputDate));
        } catch (Exception e) {
            throw new OllieException("Please enter the date in the format: yyyy-MM-dd HH:mm");
        }
    }

    @Override
    public String saveAsString() {
        DateTimeFormatter formatDate = getFormatDate();
        return String.format("%s | %s | %s", super.saveAsString(), start.format(formatDate), end.format(formatDate));
    }

    @Override
    public String toString() {
        DateTimeFormatter formatDate = getFormatDate();
        return super.toString() + " (from: " + start.format(formatDate) + " to: " + end.format(formatDate) + ")";
    }
}