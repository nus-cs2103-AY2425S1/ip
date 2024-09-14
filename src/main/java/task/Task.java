package task;

/**
 * The Task class is the base class for all Tasks
 */
public abstract class Task {
    private String description;
    private String taskType;
    private boolean isDone;


    /**
     * Constructor for a Task
     * @param description String representing the task
     * @param taskType String representing the task type
     */
    public Task(String description, String taskType) {
        this.description = description;
        this.taskType = taskType;
        this.isDone = false;
    }

    /**
     * Marks the task as done
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Marks the task as undone
     */
    public void unmark() {
        this.isDone = false;
    }

    public String getStatusIcon() {
        return this.isDone ? "[X]" : "[ ]";
    }

    /**
     * Gets task description
     *
     * @return String representing the task description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Gets task type
     * @return String representing the task type
     */
    public String getTaskType() {
        return this.taskType;
    }

    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.description;
    }
}