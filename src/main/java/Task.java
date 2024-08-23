public class Task {
    private String description;
    private boolean isDone = false;
    Task(String description) {
        this.description = description;
    }

    void mark() {
        isDone = true;
    }

    void unMark() {
        isDone = false;
    }

    private String getStatus() {
        return isDone ? "[X]" : "[ ]";
    }

    @Override
    public String toString() {
        return getStatus() + " " + this.description;
    }

}
