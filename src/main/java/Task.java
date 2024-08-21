public class Task {
    private String description;
    private boolean done;

    public Task(String description) {
        this.description = description;
        this.done = false;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public boolean isDone() {
        return done;
    }

    @Override
    public String toString() {
        String mark = done ? "X" : " ";
        return String.format("[%s] %s", mark, description);
    }
}
