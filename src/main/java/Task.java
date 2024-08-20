public class Task {
    private String taskDescription;
    private boolean isDone;

    Task(String taskDescription) {
        this.taskDescription = taskDescription;
        this.isDone = false;
    }

    @Override
    public String toString() {
        char label = this.isDone ? 'X' : ' ';
        return String.format("[%s] %s", label, this.taskDescription);
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }
}
