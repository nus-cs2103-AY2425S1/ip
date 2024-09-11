package loafy.task;

public abstract class Task {
    private boolean isDone;
    private final String name;

    public Task(boolean isDone, String name) {
        this.isDone = isDone;
        this.name = name;
    }

    public Task(String name) {
        this(false, name);
    }

    public void changeStatus(boolean isDone) {
        this.isDone = isDone;
    }

    public boolean hasMatch(String keyword) {
        return this.name.toLowerCase().contains(keyword.toLowerCase());
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), this.name);
    }

    public String convertToTxt() {
        return String.format("%s,%s", this.isDone, this.name);
    }
}
