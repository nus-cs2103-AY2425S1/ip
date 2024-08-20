public class Task {
    private String description;
    private String info;
    private boolean isDone;

    public Task(String description, String type) {
        this.description = description;
        this.info = info;
        this.isDone = false;
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        if (isDone) {
            return "[X] " + description;
        } else {
            return "[ ] " + description;
        }
    }
}
