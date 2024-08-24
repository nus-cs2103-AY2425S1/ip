public class Task {

    private final String description;
    private boolean isDone;

    public static Task createFromData(String dataLine) throws IllegalArgumentException {
        Task task;
        String[] arguments = dataLine.split("\\|");
        String taskType = arguments[0];
        boolean isDone = Boolean.parseBoolean(arguments[1]);
        String taskDescription = arguments[2];

        switch (taskType) {
        case "T":
            task = new ToDo(taskDescription);
            task.isDone = isDone;
            break;
        case "D":
            String by = arguments[3];
            task = new Deadline(taskDescription, by);
            break;
        case "E":
            String from = arguments[3];
            String to = arguments[4];
            task = new Event(taskDescription, from, to);
            break;
        default:
            throw new IllegalArgumentException("Invalid task type found in save file");

        }

	    return task;
    }

    public Task(String description) {
        this.description = description;
    }


    public void markDone() {
        isDone = true;
    }

    public void markUndone() {
        isDone = false;
    }

    private String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    public String toFileFormat() {
        return String.valueOf(isDone) + "|" + this.description;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

}
