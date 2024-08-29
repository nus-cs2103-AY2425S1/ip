package Ponder_Pika.Task;

public abstract class Task {
    private final String description;
    private boolean isDone;

    public Task(String description) {

        this.description = description;
        this.isDone = false;
    }

    public void markDone() {
        this.isDone = true;
    }

    public void markUndone() {
        this.isDone = false;
    }

    public String taskStatus() {
        return (this.isDone ? "X" : " ");
    }

    public boolean isDone() {
        return (this.isDone);
    }

    public String getDescription() {
        return (this.description);
    }

    public abstract String saveFullDetails();

    @Override
    public String toString() {
        return String.format("[%s] %s", this.taskStatus(), this.description);
    }
}
