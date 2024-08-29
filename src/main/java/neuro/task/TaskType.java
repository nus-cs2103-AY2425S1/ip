package neuro.task;

public enum TaskType {
    TODO,
    DEADLINE,
    EVENT,
    INVALID;

    public static TaskType stringToEnum(String str) {
        return switch (str) {
            case "todo" -> TODO;
            case "deadline" -> DEADLINE;
            case "event" -> EVENT;
            default -> INVALID;
        };
    }
}
