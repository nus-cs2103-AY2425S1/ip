package lawrence.task;

/**
 * Represents the different tasks that can be handled by the chatbot.
 */
public enum TaskType {
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event");

    private final String taskType;

    /** Default constructor.
     * <p>
     * The input string is converted into lowercase for greater input flexibility.
     * </p>
     *
     * @param type the string containing an enum value
     */
    private TaskType(String type) {
        this.taskType = type.toLowerCase();
    }

    public String getTaskType() {
        return taskType;
    }

    /**
     * Converts a text string into its enum counterpart.
     *
     * @param input the input containing an enum value
     * @return a TaskType enum if it exists
     * @throws IllegalArgumentException if string does not match any enum values
     */
    public static TaskType fromString(String input) throws IllegalArgumentException {
        for (TaskType task : TaskType.values()) {
            String taskType = task.getTaskType();
            // Check for exact match
            if (taskType.equalsIgnoreCase(input)) {
                return task;
            }

            // Check for match by first letter if no exact match found
            if (taskType.startsWith(String.valueOf(input.charAt(0)).toLowerCase())) {
                return task;
            }
        }
        throw new IllegalArgumentException("No task type found for input: " + input);
    }
}
