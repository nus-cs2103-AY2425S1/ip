package neon;

public class Todo extends Task {
    private final String taskType = "T";

    /**
     * Constructs a TodoTask object with the specified description.
     *
     * @param name - Description of task.
     * @param isCompleted - Whether the task is completed.
     */
    public Todo(String name, boolean isCompleted) {
        super(name, isCompleted);
    }

    /**
     * Returns the String representation of the task.
     *
     * @return String representation.
     */
    @Override
    public String toString() {
        return "[" + this.getTaskType() + "]["+ this.checkMark() + "] " + this.getName();
    }

    /**
     * Returns the String representation of the task for storage.
     *
     * @return String representation for storage.
     */
    public String toTask() {
        return this.getTaskType() + "/"+ this.completeStatus() + "/" + this.getName();
    }

    /**
     * Returns the String character representing the task type.
     *
     * @return String representation of task type.
     */
    public String getTaskType() {
        return this.taskType;
    }
}
