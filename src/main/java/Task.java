public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public boolean mark(boolean marking) {
        // Task is already marked correctly
        if (this.isDone == marking) {
            return false;
        }
        else {
            this.isDone = marking;
            return true;
        }
    }

    @Override
    public String toString() {
        return this.description;
    }
}
