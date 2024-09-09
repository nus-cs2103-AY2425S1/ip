package tasks;

/**
 * Represents a general task with a description and completion status.
 * This class serves as a base class for more specific types of tasks such as ToDos, Deadlines, and Events.
 */
public class Task {
    private String taskContent;
    private boolean isDone;

    /**
     * Constructs a Task with the specified description.
     *
     * @param taskContent The description of the task.
     */
    public Task(String taskContent) {
        this.taskContent = taskContent;
    }

    /**
     * Sets the completion status of the task.
     *
     * @param isDone The completion status of the task. True if the task is completed, false otherwise.
     */
    public void setStatus(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Returns a string representation of the task, including its completion status and description.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        String header = "";
        if (isDone) {
            header = "[X] ";
        } else {
            header = "[ ] ";
        }
        return header + this.taskContent;
    }

    /**
     * Returns a string format of the task for saving to a file.
     *
     * @return A string formatted for saving to a file.
     */
    public String toFileFormat() {
        return (isDone ? "1" : "0") + " | " + taskContent;
    }

    public String getTaskContent() {
        return this.taskContent;
    }

    public String getTaskType() {
        return "task";
    }

}
