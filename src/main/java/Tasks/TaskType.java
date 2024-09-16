package tasks;

/**
 * Tasks that can be created.
 */
public enum TaskType {
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event");

    private final String description;

    TaskType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }
}
