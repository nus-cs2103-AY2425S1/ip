public class Task {
    private boolean completed;
    private String description;

    public Task(String description) {
        this.completed = false;
        this.description = description;
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
