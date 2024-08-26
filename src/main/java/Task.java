abstract class Task {
    private String name;
    private boolean isDone;

    Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public void changeStatus(boolean isDone) {
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), this.name);
    }
}
