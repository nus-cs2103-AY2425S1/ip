package sage.Task;

/**
 * Represents the different types of tasks that Sage can manage.
 */
public enum TaskType {
    TODO,
    DEADLINE,
    EVENT,
    INVALID;

    /**
     * Converts a string to its corresponding task value.
     * If the string does not match any valid task type, INVALID is returned.
     */
    public static TaskType inputToEnum(String str) {
        return switch (str) {
        case "todo" -> TODO;
        case "deadline" -> DEADLINE;
        case "event" -> EVENT;
        default -> INVALID;
        };
    }
}
