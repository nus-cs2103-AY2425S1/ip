package dgpt.task;

/**
 * Represents a task of type "Deadline" in the Dgpt application.
 * <p>
 * A {@code Recurring} is a type of {@code Task} that is repeated after a specific timeframe.
 * It is characterized by its description, which is inherited from the {@code Task} class.
 * </p>
 */
public class Recurring extends Task {

    private String frequency;

    /**
     * Constructs a {@code Recurring} task with the specified description and frequency.
     * @param description The description of the {@code Recurring} task. This is a brief text that describes
     *                    what needs to be done.
     * @param frequency The frequency of the {@code Recurring} task, in a string format.
     */
    public Recurring(String description, String frequency) {
        super(description);
        this.frequency = frequency;
    }

    public String getFrequency() {
        return this.frequency;
    }

    @Override
    public String toString() {
        return "[R]" + super.toString() + " (every: " + frequency + ")";
    }
}
