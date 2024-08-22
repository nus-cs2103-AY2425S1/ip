public class Task {
    boolean isDone;
    String description;

    public Task(boolean isDone, String description) {
        this.isDone = isDone;
        this.description = description;
    }

    @Override
    public String toString() {
        return "[" + (isDone ? "X" : " ") + "] " + description;
    }

}
