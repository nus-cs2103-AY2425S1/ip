package task;

public abstract class Task {
    protected String type;
    protected String description;
    protected Status status;
    private static int taskCount = 0;

    public Task(String description) {
        this.description = description;
        this.status = Status.INCOMPLETE;
        taskCount++;
    }

    public void setComplete(Boolean isCompleted) {
        this.status = Status.COMPLETE;
    }

    public int getTaskCount() {
        return taskCount;
    }

    public void outputTaskCount() {
        System.out.println("You have " + taskCount + " tasks pending.");
    }

    public void deleteTask() {
        taskCount--;
    }

    @Override
    public String toString() {
        return "[" + this.status + "]" + this.description;
    }
}
