package milutrock.tasks;

/**
 * A task with a name and status whether it is complete.
 */
public class Task {
    private String name;
    private boolean isDone;

    public Task(String name) {
        this.name = name;
    }

    /**
     * Mark the task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Mark the task as not done yet.
     */
    public void unmarkDone() {
        this.isDone = false;
    }

    @Override
    public String toString() { 
        return "[" + (this.isDone ? "X" : " ") + "] " + this.name; 
    } 
}
