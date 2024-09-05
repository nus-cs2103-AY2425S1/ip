package killjoy.task;

import java.time.format.DateTimeFormatter;

/**
 * Represents the Task class of the KillJoy application.
 * Contains methods to create and manage tasks.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected TaskType taskType = null;
    protected static final DateTimeFormatter DATE_TIME_OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm:ss");

    /**
     * Represents the type of task.
     */
    public enum TaskType {
        TODO,
        DEADLINE,
        EVENT
    }

    public Task(String description, TaskType taskType) {
        this.description = description;
        this.isDone = false;
        this.taskType = taskType;
    }

    /**
     * Changes the status of the task.
     */
    public void changeStatus() {
        if (this.isDone) {
            this.isDone = false;
        } else {
            this.isDone = true;
        }
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription() {
        return this.description;
    }

    public boolean getTaskStatus() {
        return this.isDone;
    }

    public String getTaskInfo() {
        return null;
    }

    public TaskType getTaskType() {
        return this.taskType;
    }


    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
