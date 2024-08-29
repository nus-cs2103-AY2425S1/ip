package Tasks;

public abstract class Task {
    protected String description;
    protected boolean isMarked;

    public Task(String description) {
        this.description = description;
        this.isMarked = false;
    }

    public boolean mark(boolean toMark) {
        // Tasks.Task is already marked correctly
        if (this.isMarked == toMark) {
            return false;
        }
        else {
            this.isMarked = toMark;
            return true;
        }
    }

    @Override
    public String toString() {
        String mark = isMarked ? "X" : " ";
        return "[" + mark + "] " + this.description;
    }
}
