package vuewee.task;

/**
 * Enum representing the type of a task.
 */
public enum TaskType {
    TODO('T', TodoTask.class), DEADLINE('D', DeadlineTask.class), EVENT('E', EventTask.class);

    private final char taskTypeChar;
    private final Class<? extends Task> taskClass;

    /**
     * Constructs a TaskType with the specified task type character and task class.
     *
     * @param taskTypeChar the task type character
     * @param taskClass    the task class
     */
    TaskType(char taskTypeChar, Class<? extends Task> taskClass) {
        this.taskTypeChar = taskTypeChar;
        this.taskClass = taskClass;
    }

    /**
     * Returns the task type character.
     *
     * @return the task type character
     */
    public char toChar() {
        return taskTypeChar;
    }

    /**
     * Returns the TaskType enum constant associated with the specified taskType
     * character.
     *
     * @param taskType the task type character
     * @return the TaskType enum constant
     * 
     * @throws UnhandledTaskTypeException invalid task type character
     */
    public static TaskType fromChar(char taskType) {
        for (TaskType type : TaskType.values()) {
            if (type.taskTypeChar == taskType) {
                return type;
            }
        }
        throw new UnhandledTaskTypeException(taskType);
    }

    /**
     * Creates a new task instance of the associated task class with the enum value.
     *
     * @param params the task parameters
     * @return a new task instance depending on the task type
     * 
     * @throws RuntimeException if the task cannot be created
     */
    public Task createTask(String params) {
        try {
            Task task = this.taskClass.getDeclaredConstructor().newInstance();
            task.deserialize(params);
            return task;
        } catch (Exception e) {
            throw new RuntimeException("Failed to create task instance", e);
        }
    }
}
