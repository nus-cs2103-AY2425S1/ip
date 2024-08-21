package task;

public abstract class Task {
    protected String type;
    protected String description;
    protected boolean isCompleted;
    private static int taskCount = 0;

    public Task(String description) {
        this.description = description;
        this.isCompleted = false;
        taskCount++;
    }

    public void setComplete(Boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    public int getTaskCount() {
        return taskCount;
    }

    public void outputTaskCount() {
        System.out.println("You have " + taskCount + " tasks pending.");
    }

    @Override
    public String toString() {
        if (isCompleted) {
            return "[x] " + this.description;
        }
        return "[ ] " + this.description;
    }
}
