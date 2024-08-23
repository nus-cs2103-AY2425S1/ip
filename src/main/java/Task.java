public abstract class Task {
    private final String text;
    private boolean isDone;

    public Task(String text) {
        this.text = text;
        this.isDone = false;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void unmarkAsDone() {
        this.isDone = false;
    }

    private String getStatus() {
        return this.isDone ? "X" : " ";
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatus(), this.text);
    }
}