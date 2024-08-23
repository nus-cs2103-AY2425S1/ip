public class Task {
    private String taskDesc;
    private boolean isDone;

    Task(String taskDesc) {
        this.taskDesc = taskDesc;
        this.isDone = false;
    }

    public String markAsDone() {
        this.isDone = true;
        return "[X] " + this.taskDesc;
    }

    public String markAsUndone() {
        this.isDone = true;
        return "[ ] " + this.taskDesc;
    }

    public String getStatus() {
        return this.isDone ? "[X]" : "[ ]";
    }

    public String toString() {
        return this.getStatus() + " " + this.taskDesc;
    }
}
