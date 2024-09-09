package blue.task;

/**
 * Represents a task with a start and end time.
 * Inherits from the {@link blue.task.Task} class.
 */
public class EventTask extends Task {
    /** The starting time of the task. */
    private String start;

    /** The ending time of the task. */
    private String end;

    /**
     * Constructs an EventTask with a description, start time, and end time.
     *
     * @param description The description of the task.
     * @param start The starting time of the task.
     * @param end The ending time of the task.
     */
    public EventTask(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /**
     * Returns a string representation of the task, including its start and end times.
     *
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        return "[E] " + super.toString() + " (from: " + start + " to: " + end + ")";
    }

    /**
     * Returns the string representation of the task formatted for saving to a file.
     *
     * @return The string formatted for saving to a file.
     */
    @Override
    public String toFileString() {
        return "E | " + super.getStatusIcon() + " | " + getDescription() + " | " + start + " | " + end;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        EventTask other = (EventTask) obj;
        return this.getDescription().equals(other.getDescription()) &&
                this.start.equals(other.start) &&
                this.end.equals(other.end) &&
                this.isDone() == other.isDone(); // Assuming there's an isDone() method in Task class
    }

}
