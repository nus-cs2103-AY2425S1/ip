/**
 * Class that encapsulates a task that can be added to `Torne`.
 */
public class Task {
    protected final String name;
    protected boolean isDone;

    Task(String name) throws TorneInvalidCommandException {
        if (name == null || name.isEmpty()) {
            throw new TorneInvalidCommandException("Task cannot have an empty name");
        }
        this.name = name.trim();
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsNotDone() {
        isDone = false;
    }

    public Boolean getIsDone() {
        return isDone;
    }

    @Override
    public String toString() {
        String status = isDone ? "X" : " ";

        return String.format("[%s] %s", status, name);
    }
}
