package blue.task;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "1" : "0");
    }

    public String getIcon() {
        return (isDone ? "X" : " ");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUnDone() {
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }
    @Override
    public String toString() {
        String result = "[" + this.getIcon() + "] " + this.description;
        return result;
    }

    public abstract String toFileString();

    public static Task fromFileString(String fileString) {
        String[] parts = fileString.split(" \\| ");
        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        Task task;
        switch (type) {
        case "T":
            task = new ToDoTask(description);
            break;
        case "D":
            // Handle missing date
            String by = parts.length > 3 ? parts[3] : "";
            // Pass the date string directly
            task = new DeadlineTask(description, by);
            break;
        case "E":
            // Handle missing from
            String from = parts.length > 3 ? parts[3] : "";
            // Handle missing to
            String to = parts.length > 4 ? parts[4] : "";
            task = new EventTask(description, from, to);
            break;
        default:
            throw new IllegalArgumentException("Invalid task type in file: " + type);
        }

        if (isDone) {
            task.markAsDone();
        }

        return task;
    }

}