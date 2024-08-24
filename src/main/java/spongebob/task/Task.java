package spongebob.task;

/**
 * basic task
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected TaskType taskType;

    public Task(String description, TaskType taskType) {
        this.description = description;
        this.isDone = false;
        this.taskType = taskType;
    }

    public String getStatusIcon() {
        return (isDone ? "[X] " : "[ ] "); // mark done task with X
    }

    public String getDescription() {
        return this.description;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void unmarkAsDone() {
        this.isDone = false;
    }

    public String toString() {
        return this.getStatusIcon() + this.getDescription() + " ";
    }
    /**
     * converts task into a string that can be stored and read later
     * @return String version of the task
     */
    public String save() {
        return this.taskType + "|" + this.isDone + "|" + this.description;
    }


}
