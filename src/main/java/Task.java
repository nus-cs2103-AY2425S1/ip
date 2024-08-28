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
        String icon = this.getStatusIcon();
        String result ="[" + icon + "] " + this.description;
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
            task = new DeadlineTask(description, parts[3]);
            break;
        case "E":
            task = new EventTask(description, parts[3], parts[4]);
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
