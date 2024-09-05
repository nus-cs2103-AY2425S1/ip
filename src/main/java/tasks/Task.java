package tasks;

import java.util.Objects;

/**
 * The umbrella term for all Tasks
 */
public abstract class Task {

    private static int taskCount = 0;
    protected String description;
    protected boolean isDone;

    /**
     * Task constructor
     * @param description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Task task = (Task) o;
        return Objects.equals(this.description, task.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.description);
    }
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String toDataString() {
        return (isDone ? "1" : "0") + " | " + description;
    }

    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    public void setDone(boolean done) {
        this.isDone = done;
    }

    public boolean getDoneStatus() {
        return this.isDone;
    }

    public String getDes() {
        return this.description;
    }
    public static int assignTaskCountZero(int count) {
        return Task.taskCount = count;
    }
    public static int getTaskCount() {
        return Task.taskCount;
    }
    public static void incrementTaskCount() {
        Task.taskCount++;
    }
    public static void decrementTaskCount() {
        Task.taskCount--;
    }
}

