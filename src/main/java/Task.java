public class Task {
    protected String description;
    protected boolean isDone = false;

    public Task(String desc) {
        description = desc;
    }

    public void mark() {
        isDone = true;
    }

    public void unmark() {
        isDone = false;
    }

    @Override
    public String toString() {
        if (isDone) {
            return "[X] " + description;
        } else {
            return "[ ] " + description;
        }
    }

    public boolean isDone() {
        return isDone;
    }

}
