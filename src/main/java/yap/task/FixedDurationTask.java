package yap.task;

/**
 * Represents a task with a deadline
 */
public class FixedDurationTask extends Task {

    private int duration;

    /**
     * Constructs a task that has a fixed duration, with the task's description and duration.
     *
     * @param description a description of what the task is.
     * @param duration the duration required to complete the task.
     */
    public FixedDurationTask(String description, int duration) {
        super(description);
        this.duration = duration;
    }

    /**
     * Constructs a task that has a fixed duration, with the task's description and duration.
     * The task can either be done or not done.
     *
     * @param description a description of what the task is.
     * @param duration the duration required to complete the task.
     * @param isDone whether the task has been completed or not.
     */
    public FixedDurationTask(String description, int duration, boolean isDone) {
        super(description, isDone);
        this.duration = duration;
    }

    /**
     * Converts the details of the file into the format represented in the storage file.
     *
     * @return A string in the appropriate format, E | Completion Status (0 or 1) | Description | Duration
     */
    @Override
    public String convertToFileString() {
        return "F | " + super.convertToFileString() + String.format(" | %d\n", duration);
    }

    @Override
    public String toString() {
        return "[F]" + super.toString()
                + String.format(" (fixed duration: %d)", duration);
    }
}
