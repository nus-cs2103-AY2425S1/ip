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

    /**
     * Converts a short form string to the corresponding TaskType enum.
     *
     * @param shortForm The string representation of the task type (e.g., "T" for TODO).
     * @return The corresponding TaskType enum.
     * @throws IllegalArgumentException if no matching TaskType is found.
     */
    public static TaskType fromString(String shortForm) {
        for (TaskType type : TaskType.values()) {
            if (type.shortForm.equals(shortForm)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Hey, this is a uncommon task type: " + shortForm);
    }

}
