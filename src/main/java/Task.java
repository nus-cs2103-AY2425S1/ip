public abstract class Task {
    private final String description;
    private boolean complete;

    public Task(String description) {
        this.description = description;
        complete = false;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", complete ? "X" : " ", description);
    }
}
