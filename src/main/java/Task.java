/**
 * Class that encapsulates a task that can be added to `Torne`
 */
public class Task {
    private final String name;
    private boolean isDone;

    Task(String name) {
        this.name = name;
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
