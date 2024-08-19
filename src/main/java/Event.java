/**
 * Represents an Event task with a description, start time, and end time.
 */
public class Event extends Task {
    private String start;
    private String end;

    /**
     * Constructs an Event task with the specified description, start time, and end time.
     *
     * @param description The description of the Event task.
     * @param start The start time of the Event.
     * @param end The end time of the Event.
     */
    public Event(String description, String start, String end) {
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
            throw new OllieException("Please enter in the format:\n" + "event event_name /from: start_time /to: end_time");
        }
        return new Event(parts[0].trim(), parts[1].trim(), parts[2].trim());
    }

    @Override
    public String toString() {
        return super.toString() + " (from: " + start + " to: " + end + ")";
    }
}