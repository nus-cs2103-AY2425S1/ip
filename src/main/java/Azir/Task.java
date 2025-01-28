package Azir;

/**
 * Representation of a task.
 * Able to set if a task has been completed.
 */
public class Task {
    private String description;
    private boolean isDone;

    /**
     * Constructs a tasks with task description.
     *
     * @param description Task description.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void setDone() {
        this.isDone = true;
    }

    public void setNotDone() {
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    public String formatText() {
        return "";
    }

    public String getDoneString() {
        return this.isDone ? "Complete" : "Incomplete";
    }

    @Override
    public String toString() {
        if (this.isDone) {
            return String.format("[X] %s", this.description);
        }
        return String.format("[] %s", this.description);
    }
}