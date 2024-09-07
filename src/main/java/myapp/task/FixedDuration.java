package myapp.task;

/**
 * The {@code FixedDuration} class represents a task with a fixed duration but no fixed start or end time.
 * It extends the {@link Task} class and adds the concept of a fixed duration in hours.
 */
public class FixedDuration extends Task {
    private final int durationInHours;

    /**
     * Constructs a {@code FixedDuration} task with the specified description and duration.
     *
     * @param description the description of the task.
     * @param durationInHours the fixed duration of the task in hours.
     */
    public FixedDuration(String description, int durationInHours) {
        super(description);
        this.durationInHours = durationInHours;
    }

    /**
     * Returns the fixed duration of the task in hours.
     *
     * @return the duration of the task in hours.
     */
    public int getDuration() {
        return this.durationInHours;
    }

    /**
     * Returns the string representation of the {@code FixedDuration} task, including its type,
     * description, and duration.
     *
     * @return the string representation of the task.
     */
    @Override
    public String toString() {
        return "[F]" + super.toString() + " (Duration: " + durationInHours + " hours)";
    }

    /**
     * Converts the {@code FixedDuration} task to a format suitable for saving to a file.
     * The format includes the task type, completion status, description, and duration.
     *
     * @return a string representing the task in file format.
     */
    @Override
    public String toFileFormat() {
        return "F | " + (isDone ? "1" : "0") + " | " + description + " | " + durationInHours;
    }
}
