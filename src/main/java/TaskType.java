/**
 * Enum representing different types of tasks.
 * Each task type has a corresponding short form used for display.
 */
public enum TaskType {
    TODO("T"),
    DEADLINE("D"),
    EVENT("E");

    /**
     * Constructs a TaskType with the specified short form.
     *
     * @param shortForm The short form representing the task type.
     */
    private final String shortForm;

    TaskType(String shortForm) {
        this.shortForm = shortForm;
    }

    /**
     * Returns the short form of the task type.
     * This method is overridden to provide a string representation of the enum constant.
     *
     * @return The short form of the task type as a string.
     */
    @Override
    public String toString() {
        return this.shortForm;
    }
}
