public class Task {
    private String description;
    private boolean isMarked;

    public Task(String description) {
        this.description = description;
        this.isMarked = false;
    }

    public String getStatusIcon() {
        return (isMarked ? "X" : " "); // mark done task with X
    }

    public void mark() {
        this.isMarked = true;
    }

    public void unmark() {
        this.isMarked = false;
    }

    public String getDescription() {
        return this.description;
    }

    public String toSaveString() {
        return (this.isMarked ? "1" : "0") + Barney.SAVE_FILE_DELIMITER + this.description;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
