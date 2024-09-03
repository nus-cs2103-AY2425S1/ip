public abstract class Task {
    private final String description;
    private boolean complete;

    public Task(String description) {
        this.description = description;
        complete = false;
    }

    public Task(String description, boolean isComplete) {
        this.description = description;
        complete = isComplete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public String toSaveFormat() {
        return String.format("%s | %s", complete ? "1" : "0", description);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", complete ? "X" : " ", description);
    }
}
