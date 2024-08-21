public class Task {
    private boolean completed;
    private final String description;
    public Task(String description) {
        this.completed = false;
        this.description = description;
    }
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
    public boolean getCompleted() {
        return completed;
    }
    @Override
    public String toString() {
        return String.format("[%s] %s", completed ? "X" : " ", description);
    }
}