public class Task {
    private boolean isDone;
    private String description;

    public Task(boolean isDone, String description) {
        this.isDone = isDone;
        this.description = description;
    }

    @Override
    public String toString() {
        return "[" + (isDone ? "X" : " ") + "] " + description;
    }

    public void setDone(boolean status) {
        this.isDone = status;
    }

}
