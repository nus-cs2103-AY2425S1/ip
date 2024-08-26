public abstract class Task {
    private boolean completed;
    private String description;

    public Task(String description, boolean isDone) {
        this.description = description;
        if (isDone) {
            markDone();
        } else {
            markNotDone();
        }
    }

    public void markDone() {
        this.completed = true;
    }

    public void markNotDone() {
        this.completed = false;
    }

    public String getSaveTaskString() {
        if (completed) {
            return "|1|" + description;
        }

        return "|0|" + description;
    }

    @Override
    public String toString() {
        if (completed) {
            return "[X] " + description;
        } else {
            return "[ ] " + description;
        }
    }
}
