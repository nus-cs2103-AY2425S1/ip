package rex.task;

public class Task {
    private static int numberOfTasks = 0;
    private final String description;
    private boolean isDone;

    protected Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
        numberOfTasks++;
    }

    public void markDone() {
        this.isDone = true;
    }

    public void unmarkDone() {
        this.isDone = false;
    }

    private String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    public static int getNumberOfTasks() {
        return numberOfTasks;
    }

    public static void removeTask() {
        numberOfTasks--;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    public String formatted() {
        return (isDone ? "1" : "0") + " | " + description;
    }
}
