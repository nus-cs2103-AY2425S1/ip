public abstract class Task {
    protected final String text;
    protected boolean isDone;

    public Task(String text, boolean isDone) {
        this.text = text;
        this.isDone = isDone;
    }
    public abstract String export();

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