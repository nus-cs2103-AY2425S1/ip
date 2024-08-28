package yapbot.tasks;

/**
 * Parent class of all tasks that can be created for YapBot.
 */
public class Task {
    private String description;
    private boolean isDone;

    /**
     * Returns a Task instance.
     *
     * @param description Details of the Task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        if (this.isDone) {
            return "[X] " + this.description;
        } else {
            return "[ ] " + this.description;
        }
    }
}
