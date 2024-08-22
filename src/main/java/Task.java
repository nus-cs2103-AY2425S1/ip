public class Task {
    private String description;
    private boolean isDone; // true for done, false for not done
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }
    @Override
    public String toString() {
        String completion;
        if (this.isDone) {
            completion = "[X] ";
        } else {
            completion = "[ ] ";
        }
        return completion + this.description;
    }
}
