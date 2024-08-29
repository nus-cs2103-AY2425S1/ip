package Mediell;

import java.util.Objects;

/** Represents a Task. */
public class Task {
    private String taskName;
    private boolean completed;

    public Task(String taskName) {
        this.taskName = taskName;
        completed = false;
    }

    /**
     * Marks the task as completed.
     */
    public void markAsCompleted() {
        completed = true;
    }

    /**
     * Marks the task as uncompleted.
     */
    public void markAsUncompleted() {
        completed = false;
    }

    @Override
    public String toString() {
        String status = "";
        if (completed) {
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
        return (completed ? "1" : "0") + "|" + taskName;
    }

    /**
     * Initialises the class with the storage format.
     * @param format the format from taskToStorageFormat
     */
    public void initStorageFormat(String format) {
        String[] temp = format.split("\\|", 2);
        completed = Objects.equals(temp[0], "1");
        taskName = temp[1];
    }
}
