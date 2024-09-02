import java.io.IOException;

public abstract class Task
{
    public String description;
    public boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    public String getStatus() {
        return (isDone ? "X" : " ");
    }

    public abstract String write();

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatus(), description);
    }
}
