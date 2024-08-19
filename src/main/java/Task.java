public class Task {
    private String taskName;
    private boolean isChecked;

    Task(String task) {
        this.taskName = task;
        isChecked = false;
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
