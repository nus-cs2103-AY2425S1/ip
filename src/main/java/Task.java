public class Task {
    private final String taskName;
    private boolean markDone = false;

    public Task(String taskName) {
        this.taskName = taskName;
    }

    public void setMarkDone(boolean marked) {
        this.markDone = marked;
    }

    @Override
    public String toString() {
        String mark = this.markDone ? "X" : " ";
        return "[" + mark + "] " + this.taskName;
    }
}
