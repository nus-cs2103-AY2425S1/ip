public class Task {
    private final String description;
    private boolean done;

    public Task(String description) throws IllegalArgumentException {
        if (description.isEmpty()) {
            throw new IllegalArgumentException("You can't do a non-existent task.");
        }

        this.description = description;
        this.done = false;
    }

    public String getDescription() {
        return description;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public boolean isDone() {
        return done;
    }
}
