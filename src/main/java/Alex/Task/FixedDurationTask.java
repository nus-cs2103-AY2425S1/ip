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

    @Override
    public TaskType getTaskType() {
        return TaskType.FIXED_DURATION;
    }

    @Override
    public String toString() {
        return "[F][ ] " + getDescription() + " (Duration: " + durationInMinutes + " minutes)";
    }
}
