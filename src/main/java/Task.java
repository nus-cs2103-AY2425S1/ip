public class Task {
    private String description;
    private boolean isdone;

    public Task(String description) {
        this.description = description;
        this.isdone = false;
    }

    public void markAsDone() {
        this.isdone = true;
    }

    public void markAsUndone() {
        this.isdone = false;
    }
}