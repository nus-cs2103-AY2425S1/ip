package data;

public abstract class Task {
    private boolean isComplete;
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
        this.isComplete = true;
    }

    public void markNotDone() {
        this.isComplete = false;
    }

    public String getSaveTaskString() {
        if (this.isComplete) {
            return "|1|" + description;
        }

        return "|0|" + description;
    }

    @Override
    public String toString() {
        if (this.isComplete) {
            return "[X] " + description;
        } else {
            return "[ ] " + description;
        }
    }
}
