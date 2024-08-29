package LBot.task;

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

    public void mark() {
        this.isComplete = !this.isComplete;
    }

    protected String status() {
        if (isComplete) {
            return "x";
        }
        return " ";
    }

    public boolean getStatus() {
        return isComplete;
    }

    @Override
    public String toString() {
        return "[" + status() + "] " + this.description;
    }
}
