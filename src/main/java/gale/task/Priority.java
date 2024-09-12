package gale.task;

/**
 * Represents the priority of a task.
 * <p>A task can have only one out of the 3 priorities: low, medium, or high.</p>
 */
public enum Priority {
    NONE(0),
    LOW(1),
    MEDIUM(2),
    HIGH(3);

    private final int level;

    Priority(int level) {
        this.level = level;
    }

    /**
     * Returns the level of the priority, either 0, 1, 2, or 3.
     * @return the level of the priority
     */
    public int getLevel() {
        return level;
    }

    /**
     * Returns the priority as a String.
     * @return a String representation of the priority
     */
    public String toString() {
        switch (this) {
        case LOW:
            return "low";
        case MEDIUM:
            return "medium";
        case HIGH:
            return "high";
        case NONE:
            return "none";
        default:
            return "";
        }
    }
}
