public class Task {
    private String description;
    private boolean isDone;
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void setDone() {
        this.isDone = true;
    }

    public void setNotDone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        if (this.isDone) {
            return String.format("[X] %s", this.description);
        }
        return String.format("[] %s", this.description);
    }
}