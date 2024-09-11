package froggy;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void setStatus(boolean newStatus) {
        this.isDone = newStatus;
    }

    public String getDescription() {
        return description;
    }

    /**
     * Returns how the task is stored on ./data/taskList.txt as a string.
     */
    public String toTxt() {
        return (isDone ? "1 " : "0 ") + this.description;
    }

    public boolean equals(Task task) {
        if (this == task) {
            return true;
        }
        if (task == null || getClass() != task.getClass()) {
            return false;
        }
        return description.equals(task.getDescription());
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
