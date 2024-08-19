public class Task {
    private String taskName;
    private boolean isChecked;

    Task(String task) {
        this.taskName = task;
        isChecked = false;
    }

    public void mark() {
        this.isChecked = true;
    }

    public void unMark() {
        this.isChecked = false;
    }

    @Override
    public String toString() {
        if (isChecked) {
            return "[X] " + this.taskName;
        } else {
            return "[ ] " + this.taskName;
        }
    }
}
