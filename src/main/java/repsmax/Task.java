package repsmax;
public class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }
    public void setDone() {
        isDone = true;
    }
    public void setUndone() {
        isDone = false;
    }
    public boolean isDone() {
        return isDone;
    }
    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    public String toFileFormat() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }
    public static Task fromFileFormat(String fileFormat) {
        String[] parts = fileFormat.split(" \\| ");
        if (parts.length != 3) {
            throw new IllegalArgumentException("Invalid task format.");
        }
        String description = parts[2];
        boolean isDone = parts[1].equals("1");
        Task task = new Task(description);
        if (isDone) {
            task.setDone();
        }
        return task;
    }
}
