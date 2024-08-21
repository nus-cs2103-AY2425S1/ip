public abstract class Task {
    private boolean completed;
    private String description;

    public Task(String description) {
        this.completed = false;
        this.description = description;
    }

    public void markDone() {
        this.completed = true;
    }

    public void markNotDone() {
        this.completed = false;
    }

    @Override
    public String toString() {
        if (completed) {
            return "[X] " + description;
        } else {
            return "[ ] " + description;
        }
    }
}
