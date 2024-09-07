package Task;

public class Task {
    public enum TaskType {
        T,
        D,
        E,
    }
    private String taskName;
    private boolean isCompleted;
    private TaskType taskType;
    public Task(String name, TaskType taskType) {
        this.taskName = name;
        this.isCompleted = false;
        this.taskType = taskType;

    }

    /**
     * Marks the current task as done.
     */
    public void markAsDone() {
        this.isCompleted = true;
    }


    /**
     * Marks the current task as undone.
     */
    public void markAsUndone() {
        this.isCompleted = false;
    }

    /**
     * Creates a task with given string.
     *
     * @param name the string containing information about the task type
     * @param taskType the type of task
     * @throws TaskCreationException if error occurs while creating task
     */
    public static Task of(String name, TaskType taskType) throws TaskCreationException {
        assert !name.isEmpty() : "Task name should not be empty";
        return new Task(name, taskType);
    }

    /**
     * Returns a task with given string.
     *
     * @return  the task and its given information as a string
     */
    public String getTaskName() {
        return this.taskName;
    }

    /**
     * Returns the status of the task.
     *
     * @return  the status of the task as a string
     */
    public String getStatus(){
        return this.isCompleted ? "X": " ";
    }

    /**
     * Returns the task type.
     *
     * @return  the task type as a string
     */
    public String getTaskTypeAsString(){
        if (this.taskType == Task.TaskType.T) {
            return "T";
        } else if (this.taskType == Task.TaskType.D) {
            return "D";
        } else {
            return "E";
        }
    }
}