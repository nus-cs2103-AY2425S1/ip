package Alex.Task;

/**
 * Represents a task with a fixed duration but no specific start or end time.
 */
public class FixedDurationTask extends Task {

    private final int durationInMinutes; // Duration in minutes

    /**
     * Constructs a FixedDurationTask with the given description and duration.
     *
     * @param description The description of the task.
     * @param durationInMinutes The duration of the task in minutes.
     */
    public FixedDurationTask(String description, int durationInMinutes) {
        super(description);
        this.durationInMinutes = durationInMinutes;
    }

    /**
     * Returns the duration of the task in minutes.
     *
     * @return The duration of the task in minutes.
     */
    public int getDurationInMinutes() {
        return durationInMinutes;
    }

    /**
     * Gets the type of the task.
     * @return The type of the task (FIXED_DURATION).
     */
    @Override
    public TaskType getTaskType() {
        return TaskType.FIXED_DURATION;
    }

    /**
     * Gets the string representation of the FixedDuration task.
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        return "[F][ ] " + getDescription() + " (Duration: " + durationInMinutes + " minutes)";
    }
}
