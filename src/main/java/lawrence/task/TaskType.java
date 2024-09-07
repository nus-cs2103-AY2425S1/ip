package lawrence.task;

/**
 * Represents the different tasks that can be handled by the chatbot.
 */
public enum TaskType {
    DEADLINE("deadline"),
    EVENT("event"),
    TODO("todo");

    private String taskType;

    /**
     * Default constructor.
     * <p>
     * The input string is converted into lowercase for greater input flexibility.
     * </p>
     *
     * @param type the string containing an enum value
     */
    TaskType(String type) {
        this.taskType = type.toLowerCase();
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

    /**
     * Returns the task type as a string.
     *
     * @return the task type as a string
     */
    public String getTaskType() {
        return taskType;
    }
}
