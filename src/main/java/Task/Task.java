package Task;

public  abstract class Task {
    private String taskName;
    private boolean completed;

    public Task(String s) {
        this.completed = false;
        this.taskName = s;
    }

    public String getTask() {
        return this.taskName;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void markAsDone() {
        this.completed = true;
    }

    public void markAsUnDone() {
        this.completed = false;
    }

    @Override
    public String toString() {
        String isCompleted = this.completed ? "X" : " ";
        return "[" + isCompleted + "] " + this.taskName;
    }
}
