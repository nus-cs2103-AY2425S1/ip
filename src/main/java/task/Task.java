package task;

public class Task {
    protected String description;
    protected boolean isDone;

    protected String type;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); //mark done task with X
    }

    public String getDescription() {
        return this.description;
    }

    public void markTaskAsDone() {

        this.isDone = true;
    }

    public void unmarkTask() {

        this.isDone = false;
    }

    public String toFile() {
        return "Undefined task.";
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
