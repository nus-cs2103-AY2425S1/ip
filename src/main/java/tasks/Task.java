package tasks;

public class Task {

    private boolean done;
    private String name;

    public Task(String name) {
        this.name = name;
        this.done = false;
    }

    public Task(String name, boolean done) {
        this.name = name;
        this.done = done;
    }

    /**
     * Marks the task as done.
     */
    public void mark() {
        this.done = true;
    }

    /**
     * Marks the task as not done.
     */
    public void unmark() {
        this.done = false;
    }

    public boolean isDone() {
        return this.done;
    }

    /**
     * Returns string format of the Task.
     *
     * @return String format of the Task.
     */
    @Override
    public String toString() {
        return "[" + (this.done ? "X" : " ") + "] " + this.name;
    }

    /**
     * Returns data format of the Task.
     *
     * @return Data format of the Task.
     */
    public String toData() {
        return (this.done ? "1" : "0") + this.name;
    }
}
