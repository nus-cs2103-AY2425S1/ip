public class Task {
    private boolean isDone;
    private String description;

    public Task(String description) {
        this.isDone = false;
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
