package elara.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Represents a task that is an event with a start and end time.
 * This class extends the abstract Task class and includes start and end times for the event.
 */
public class EventTask extends Task {
    protected LocalDateTime start;
    protected LocalDateTime end;

    /**
     * Constructs a new EventTask with the specified description, start time, end time, and completion status.
     *
     * @param desc The description of the event.
     * @param start The start time of the event, represented as a LocalDateTime object.
     * @param end The end time of the event, represented as a LocalDateTime object.
     * @param isDone The completion status of the task (true if the task is completed, false otherwise).
     */
    public EventTask(String desc, LocalDateTime start, LocalDateTime end, boolean isDone) {
        super(desc, isDone);
        this.start = start;
        this.end = end;
        assert start != null && end != null : "start and end should not be empty";
    }

    @Override
    public String toString() {
        assert !description.isEmpty() : description;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm a");
        return "[E]" + super.toString() + " (from: " + start.format(formatter)
                + " to: " + end.format(formatter) + ")";
    }

    /**
     * Returns the string format of the event task to be saved in a file.
     * The format includes the task type ("E"), the completion status (1 for done, 0 for not done),
     * the task description, the start time, and the end time in ISO format (yyyy-MM-dd'T'HH:mm).
     *
     * @return A string representation of the event task in file format.
     */
    @Override
    public String toFileFormat() {
        return String.format("E | %d | %s | %s | %s", isDone ? 1 : 0, description,
                start, end);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EventTask)) {
            return false;
        }
        EventTask that = (EventTask) o;
        return isDone == that.isDone && description.equals(that.description)
                && start.equals(that.start) && end.equals(that.end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, start, end);
    }
}
