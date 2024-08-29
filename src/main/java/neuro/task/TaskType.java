package neuro.task;

/**
 * The {@code TaskType} enum represents the different types of tasks
 * that can be managed in the Neuro application.
 */
public enum TaskType {
    TODO,
    DEADLINE,
    EVENT,
    INVALID;

    /**
     * Converts a string to its corresponding {@code TaskType} enum value.
     * If the string does not match any valid task type, INVALID is returned.
     *
     * @param str the string representing the task type
     * @return the corresponding {@code TaskType} enum value, or INVALID if the string is not recognized
     */
    public static TaskType stringToEnum(String str) {
        return switch (str) {
            case "todo" -> TODO;
            case "deadline" -> DEADLINE;
            case "event" -> EVENT;
            default -> INVALID;
        };
    }
}
