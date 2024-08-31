
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
                String by = parts.length > 3 ? parts[3] : "";  // Handle missing date
                task = new DeadlineTask(description, by);      // Pass the date string directly
                break;
            case "E":
                String from = parts.length > 3 ? parts[3] : "";  // Handle missing from
                String to = parts.length > 4 ? parts[4] : "";    // Handle missing to
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