package mediell.task;

import java.util.Objects;

/** Represents a Task. */
public class Task {
    private String taskName;
    private boolean isCompleted;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isCompleted = false;
    }
    
    public boolean find(String keyword) {
        return taskName.contains(keyword);
    }

    /**
     * Marks the task as completed.
     */
    public void markCompleted() {
        isCompleted = true;
    }

    /**
     * Marks the task as uncompleted.
     */
    public void markUncompleted() {
        isCompleted = false;
    }

    @Override
    public String toString() {
        String status = "";
        if (isCompleted) {
            status = "[X] ";
        } else {
            status = "[ ] ";
        }
        return status + taskName;
    }

    /**
     * Gets the format to store the task.
     * @return the format to store the task
     */
    public String taskToStorageFormat() {
        return (isCompleted ? "1" : "0") + "|" + taskName;
    }

    /**
     * Initialises the class with the storage format.
     * @param format the format from taskToStorageFormat
     */
    public void initStorageFormat(String format) {
        String[] temp = format.split("\\|", 2);
        isCompleted = Objects.equals(temp[0], "1");
        taskName = temp[1];
    }
}
