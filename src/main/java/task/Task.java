package task;

public abstract class Task {
    protected String type;
    protected String description;
    protected boolean isCompelete;

    Task(String description) {
        this.description = description;
        this.isCompelete = false;
    }

    Task(String description, boolean isComplete) {
        this.description = description;
        this.isCompelete = isCompelete;
    }

    public void setComplete(Boolean isCompleted) {
        this.isCompelete = true;
    }

    protected String status() {
        if (isCompelete) {
            return "x";
        }
        return " ";
    }

    @Override
    public String toString() {
        return "[" + status() + "] " + this.description;
    }
}
