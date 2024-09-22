package killjoy.task;

import java.time.format.DateTimeFormatter;

/**
 * Represents the Task class of the KillJoy application.
 * Contains methods to create and manage tasks.
 */
public class Task {
    protected static final DateTimeFormatter DATE_TIME_OUTPUT_FORMATTER =
            DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm:ss");
    protected String description;
    protected boolean isDone;
    protected TaskType taskType = null;


    /**
     * Represents the type of task.
     */
    public enum TaskType {
        TODO,
        DEADLINE,
        EVENT
    }

    /**
     * Constructor for the Task class.
     *
     * @param description The description of the task.
     * @param taskType    The type of task.
     */
    public Task(String description, TaskType taskType) {
        this.description = description;
        this.isDone = false;
        this.taskType = taskType;
    }

    /**
     * Changes the status of the task to done.
     */
    public void changeStatusToDone() {
        this.isDone = true;
    }

    /**
     * Changes the status of the task to not done.
     */
    public void changeStatusToNotDone() {
        this.isDone = false;
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
