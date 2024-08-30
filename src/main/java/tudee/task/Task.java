package tudee.task;

public abstract class Task {
    protected String taskString;
    protected boolean done;

    public Task(String taskString) {
        this.taskString = taskString;
        this.done = false;
    }

    public String getDescription() {
        return this.taskString;
    }

    public String getDone() {
        return (this.done ? "[X]" : "[ ]");
    }

    public void markAsDone() {
        this.done = true;
    }

    public void markAsNotDone() {
        this.done = false;
    }

    public abstract String toFileString();

    public String toString() {
        return this.getDone() + " " + this.taskString;
    }
}
