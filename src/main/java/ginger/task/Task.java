package ginger.task;

/**
 * This abstract class encapsulates a ginger.task.Task which contains a title and completion.
 * @author Say Glenn
 */
public abstract class Task {
    private final String title;
    private boolean isCompleted;

    /**
     * Constructs a ginger.task.Task given title only.
     * @param title A string which is the title of the task.
     */
    public Task(String title) {
        this.title = title;
        this.isCompleted = false;
    }

    /**
     * Constructs a ginger.task.Task given title and completion status.
     * @param title A string which is the title of the task.
     * @param isCompleted A boolean which shows completion status of task.
     */
    public Task(String title, boolean isCompleted) {
        this.title = title;
        this.isCompleted = isCompleted;
    }

    /**
     * Marks the task as complete or incomplete based on the given status.
     * @param status A boolean to indicate task completion status.
     */
    public void updateStatus(boolean status) {
        this.isCompleted = status;
    }

    public String toDbString() {
        return String.format("| %s | %s |", this.isCompleted ? "1" : "0", this.title);
    }

    public String getTitle() {
        return this.title;
    }

    @Override
    public String toString() {
        return isCompleted
                ? "[X] " + this.title
                : "[ ] " + this.title;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Task t) {
            return t.title.equals(this.title) && this.isCompleted == t.isCompleted;
        }
        return false;
    }
}
