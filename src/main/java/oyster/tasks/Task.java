package oyster.tasks;

public abstract class Task {
    private boolean isComplete = false;
    private String description;

    public static enum TASK_TYPES {
        TODO,
        DEADLINE,
        EVENT
    }

    public Task(String description) {
        this.description = description;
    }

    public void mark() {
        isComplete = true;
    }

    public void unmark() {
        isComplete = false;
    }

    public boolean isMarked() {
        return isComplete;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "[" + (isComplete ? "X" : " ") + "] " + description;
    }

    public abstract String[] compose();
}
