public enum TaskType {
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event");

    private final String type;

    private TaskType(String type) {
        this.type = type.toLowerCase();
    }

    public String getTaskType() {
        return type;
    }

    /**
     * Converts a text string into its relevant enum counterpart.
     * Throws an IllegalArgumentException if string does not match any enum values
     *
     * @param input the input containing an enum value
     * @return a TaskType enum if it exists
     */
    public static TaskType fromString(String input) {
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
