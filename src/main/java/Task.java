public class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public Task() {
        this.description = "";
        this.isDone = false;
    }

    public void check() {
        this.isDone = true;
    }

    public void uncheck() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        if (this.isDone) {
            return String.format("[X] %s", this.description);
        } else {
            return String.format("[ ] %s", this.description);
        }
    }
}
