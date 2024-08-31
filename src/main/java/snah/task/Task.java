package snah.task;

public class Task {

    private boolean isDone = false;
    private String description;

    public Task(String description) {
        this.description = description;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void unmarkAsDone() {
        this.isDone = false;
    }

    public boolean isDone() {
        return isDone;
    }

    public String printStatus() {
        return String.format("[%s]", isDone ? "X" : " ");
    }

    public String getDescription() {
        return description;
    }

    public String toSaveFile() {
        return String.format("T:%s:%s", isDone() ? "x" : "", getDescription());
    }

    @Override
    public String toString() {
        return String.format("%s %s", printStatus(), getDescription());
    }

}
