public class Task {
    private String taskName;
    private boolean completed;

    public Task(String taskName) {
        this.taskName = taskName;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        String x;
        if (completed) {
            x = "X";
        } else {
            x = " ";
        }
        return String.format("[%s] %s", x, this.taskName);
    }
}
