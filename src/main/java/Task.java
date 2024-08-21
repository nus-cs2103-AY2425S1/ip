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

    public String getStatus() {
        return String.format("[%s]", isDone ? "X" : " ");
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return String.format("%s %s", getStatus(), getDescription());
    }

}
