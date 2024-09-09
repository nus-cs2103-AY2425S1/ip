package bob;

/**
 * Represents a fixed task in the Bob chatbot.
 * The fixed task includes a fixed time taken for it to be completed.
 */
public class Fixed extends Task {
    private int durationInHours;

    /**
     * Constructs a DurationTask with a description and a fixed duration.
     *
     * @param description The description of the task.
     * @param durationInHours The duration of the task in hours.
     * @param type The task type (e.g., EVENT).
     */
    public Fixed(String description, int durationInHours, TaskType type) throws ChatBotException {
        super(description, type);
        if (durationInHours <= 0) {
            throw new ChatBotException("Duration must be a positive integer");
        }
        this.durationInHours = durationInHours;
    }

    /**
     * Constructs a DurationTask with a description and a fixed duration.
     *
     * @param description The description of the task.
     * @param isDone The completion status of the fixed task.
     * @param durationInHours The duration of the task in hours.
     * @param type The task type (e.g., EVENT).
     */
    public Fixed(String description, boolean isDone, int durationInHours, TaskType type) throws ChatBotException {
        super(description, type);
        if (durationInHours <= 0) {
            throw new ChatBotException("Duration must be a positive integer");
        }
        this.durationInHours = durationInHours;
    }

    /**
     * Gets the duration of the task.
     *
     * @return The duration in hours.
     */
    public int getDurationInHours() {
        return durationInHours;
    }

    /**
     * Returns a string representation of the DurationTask, including the description and duration.
     *
     * @return The formatted string for the task.
     */
    @Override
    public String toString() {
        return super.toString() + " (needs " + this.durationInHours + " hour(s))";
    }

    /**
     * Formats the task for saving to a file.
     *
     * @return The string to be saved in the file.
     */
    @Override
    public String formatToSave() {
        return super.formatToSave() + " | " + this.durationInHours;
    }
}
