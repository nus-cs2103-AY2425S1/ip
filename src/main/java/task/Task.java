package task;

public class Task {

    private boolean done;
    private String taskName;

    protected Task(String name) {
        this.done = false;
        this.taskName = name.trim();
    }

    protected Task(String name, boolean isDone) {
        this.done = isDone;
        this.taskName = name.trim();
    }

    public boolean isDone() {
        return this.done;
    }

    public String getName() {
        return this.taskName;
    }

    public String toFileString() {
        String done = this.isDone() ? "1" : "0";
        return "T," + done + "," + this.getName();
    }

    public void mark() {
        this.done = true;
    }

    public void unmark() {
        this.done = false;
    }

    @Override
    public String toString() {
        return (this.done ? "[X] " : "[ ] ") + this.taskName;
    }
}
