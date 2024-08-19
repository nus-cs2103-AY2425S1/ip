public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String desc) {
        description = desc;
    }

    public void mark() {
        isDone = true;
    }

    public void unmark() {
        isDone = false;
    }

    public String toString() {
        if (isDone) {
            return "[X] " + this.toString();
        } else {
            return "[ ] " + this.toString();
        }
    }

    public boolean isDone() {
        return isDone;
    }

}
