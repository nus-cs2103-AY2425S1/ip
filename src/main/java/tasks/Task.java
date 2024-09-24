package tasks;

public class Task {

    private boolean isDone;
    private String name;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    /**
     * Marks the task as isDone.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Marks the task as not isDone.
     */
    public void unmark() {
        this.isDone = false;
    }

    public boolean containsWord(String word) {
        return this.name.contains(word);
    }

    /**
     * Returns string format of the Task.
     *
     * @return String format of the Task.
     */
    @Override
    public String toString() {
        return "[" + (this.isDone ? "X" : " ") + "] " + this.name;
    }

    /**
     * Returns data format of the Task.
     *
     * @return Data format of the Task.
     */
    public String toData() {
        return (this.isDone ? "1" : "0") + this.name;
    }
}
