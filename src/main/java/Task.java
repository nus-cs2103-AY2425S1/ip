public class Task {
    private boolean completed;
    private String description;

    public Task(String s) {
        this.completed = false;
        this.description = s;
    }

    @Override
    public String toString() {
        if (this.completed) {
            return String.format("[X] %s", this.description);
        } else {
            return String.format("[ ] %s", this.description);
        }
    }

    public String getDescription() {
        return this.description;
    }

    // Indicate task as completed
    public void indComplete() {
        this.completed = true;
    }

    // Indicate task as incomplete
    public void indIncomplete() {
        this.completed = false;
    }
}
