public class Task {
    private boolean isDone;
    private String title;

    public Task(String title) {
        this.isDone = false;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    @Override
    public String toString() {
        return (isDone ? "[X] " : "[ ] ") + title;
    }
}
