public class Task {
    private String description;
    private boolean done;

    public Task (String description) {
        this.description = description;
        this.done = false;
    }

    public String getTaskString(int i) {
        return String.format("%d. [%s] %s\n", i, done?"x" :" ", description);
    }

    public String getDescription() {
        return String.format(description);
    }
    public void markAsDone() {
        done = true;
    }

    public void markAsUndone() {
        done = false;
    }
}
