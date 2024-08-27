package vuewee.task;

public enum TaskType {
    TODO('T', TodoTask.class),
    DEADLINE('D', DeadlineTask.class),
    EVENT('E', EventTask.class);

    private final char taskTypeChar;
    private final Class<? extends Task> taskClass;

    TaskType(char taskTypeChar, Class<? extends Task> taskClass) {
        this.taskTypeChar = taskTypeChar;
        this.taskClass = taskClass;
    }

    public char toChar() {
        return taskTypeChar;
    }

    public static TaskType fromChar(char taskType) {
        for (TaskType type : TaskType.values()) {
            if (type.taskTypeChar == taskType) {
                return type;
            }
        }
        throw new UnhandledTaskTypeException(taskType);
    }

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
