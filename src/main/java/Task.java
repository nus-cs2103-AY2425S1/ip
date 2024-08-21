public class Task {
    private String body;
    private boolean isDone;

    public Task(String body) {
        this.body = body;
        this.isDone = false;
    }

    public void setDone() {
        this.isDone = true;
    }

    public void setUndone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return this.isDone
                ? "[X] " + this.body
                : "[ ] " + this.body;
    }
}
