package tasks;

import java.util.Objects;

/**
 * Task represents a task that can be added to the list of tasks
 */
public abstract class Task {

    private static int taskCount = 0;
    protected String description;
    protected boolean isDone;
    private TaskPriority priority;

    /**
     * Task constructor
     * @param description the description of the task
     * @param priority the priority of the task
     */
    public Task(String description, int priority) {
        this.description = description;
        this.isDone = false;
        this.priorityAssignment(priority);
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
    /**
     * Returns the done status icon of the task
     * @return the done status icon of the task
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns the string representation of the task to be written to OrangeCat.txt
     * @return the string representation of the task
     */
    public String toDataString() {
        return (isDone ? "1" : "0") + " | " + description;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    public void setDone(boolean done) {
        this.isDone = done;
    }

    /**
     * Returns the description of the task
     * @return the description of the task
     */
    public String getDes() {
        return this.description;
    }
    /**
     * Returns the number of tasks in the list
     * @return the number of tasks in the list
     */
    public static int assignTaskCountZero(int count) {
        return Task.taskCount = count;
    }
    public static int getTaskCount() {
        return Task.taskCount;
    }
    /**
     * Increments the number of tasks in the list
     */
    public static void incrementTaskCount() {
        Task.taskCount++;
    }
    public static void decrementTaskCount() {
        Task.taskCount--;
    }
    public static TaskPriority getPriority(Task task) {
        return task.priority;
    }
    /**
     * Assigns the priority level of a task
     * @param priorityAssigned the priority level assigned to the task
     */
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
     * @param task the task to be modified
     * @param taskPriority the new priority level
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

