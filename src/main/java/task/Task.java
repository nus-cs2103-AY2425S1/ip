package task;

public abstract class Task {
    protected String type;
    protected String description;
    protected boolean isComplete;

    Task(String description) {
        this.description = description;
        this.isComplete = false;
    }

    Task(String description, boolean isComplete) {
        this.description = description;
        this.isComplete = isComplete;
    }

    public void setComplete(Boolean isCompleted) {
        this.isComplete = true;
    }

    protected String status() {
        if (isComplete) {
            return "x";
        }
        return " ";
    }

    @Override
    public String toString() {
        return "[" + status() + "] " + this.description;
    }
}
