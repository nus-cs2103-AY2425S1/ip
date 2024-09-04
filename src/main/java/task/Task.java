package task;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X] " : "[ ] "); // mark done task with X
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsUndone() {
        isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public abstract String getTaskType();

    public abstract String printTask();

    public static Task fromString(String taskString) {
        String[] parts = taskString.split(" \\| ");
        if (parts.length == 3) {
            String type = parts[0];
            boolean isDone = parts[1].equals("1");
            String description = parts[2];
            return new TodoTask(isDone, description);
        } else if (parts.length == 4) {
            String type = parts[0];
            boolean isDone = parts[1].equals("1");
            String description = parts[2];
            String dateTime = parts[3];
            return new EventTask(isDone, description, dateTime);
        } else if (parts.length == 5) {
            String type = parts[0];
            boolean isDone = parts[1].equals("1");
            String description = parts[2];
            String from = parts[3];
            String to = parts[4];
            return new DeadlineTask(isDone, description, from, to);
        }

        return new TodoTask("");
    }

    @Override
    public abstract String toString();
}
