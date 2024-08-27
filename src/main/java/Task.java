import java.util.Objects;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String getDescription() {
        return description;
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsUndone() {
        isDone = false;
    }

    public String saveString() {
        return "placeholder";
    }

    public static Task fromString(String taskString) {
        String[] parser = taskString.split(" \\| ");
        String type = parser[0];
        String description = parser[1];
        boolean isDone = Boolean.parseBoolean(parser[2]);

        if (Objects.equals(type, "T")) {
            return new ToDo(description, isDone);
        } else if (Objects.equals(type, "D")) {
            String deadline = parser[3];
            return new Deadline(description, isDone, deadline);
        } else {
            String from = parser[3];
            String to = parser[4];
            return new Event(description, isDone, from, to);
        }

    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }
}