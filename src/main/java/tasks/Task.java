package tasks;

import java.util.Objects;

/**
 * The umbrella term for all Tasks
 */
public abstract class Task {

    private static int taskCount = 0;
    protected String description;
    protected boolean isDone;
    private TaskPriority priority;

    /**
     * Task constructor
     * @param description
     */
    public Task(String description, int priority) {
        this.description = description;
        this.isDone = false;
        this.priorityAssignment(priority);
        //this.priority = priority;
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
    public static TaskPriority getPriority(Task task) {
        return task.priority;
    }
    private void priorityAssignment(int priorityAssigned) {
        if (priorityAssigned == 1) {
            this.priority = TaskPriority.LOW;
        } else if (priorityAssigned == 2) {
            this.priority = TaskPriority.MEDIUM;
        } else if (priorityAssigned == 3) {
            this.priority = TaskPriority.HIGH;
        } else if (priorityAssigned == 4) {
            this.priority = TaskPriority.CRITICAL;
        }
    }
    public int getPriorityNum() {
        if (this.priority == TaskPriority.LOW) {
            return 1;
        } else if (this.priority == TaskPriority.MEDIUM) {
            return 2;
        } else if (this.priority == TaskPriority.HIGH) {
            return 3;
        } else if (this.priority == TaskPriority.CRITICAL) {
            return 4;
        }
        return -1;
    }
    /**
     * Modifies the level of priority of a specific task instance
     * @param task
     * @param taskPriority
     */
    public static void changePriority(Task task, int taskPriority) {
        if (taskPriority == 1) {
            task.priority = TaskPriority.LOW;
        } else if (taskPriority == 2) {
            task.priority = TaskPriority.MEDIUM;
        } else if (taskPriority == 3) {
            task.priority = TaskPriority.HIGH;
        } else {
            task.priority = TaskPriority.CRITICAL;
        }
    }
}

