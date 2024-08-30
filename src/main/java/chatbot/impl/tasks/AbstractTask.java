package chatbot.impl.tasks;

import chatbot.Task;

import java.time.format.DateTimeFormatter;

public abstract class AbstractTask implements Task {

    protected static final DateTimeFormatter DATE_OUTPUT_FORMAT = DateTimeFormatter.ofPattern("dd MMM yyyy");

    protected final String description;

    protected boolean isDone;

    public AbstractTask(String description) {
        this.description = description;
        isDone = false;
    }

    public static Task deserialize(String line) {
        String[] parts = line.split(" \\| ");
        if (parts.length < 3) {
            throw new IllegalArgumentException("Invalid task format");
        }

        String type = parts[0];

        return switch (type) {
            case "T" -> TodoTask.deserialize(line);
            case "D" -> DeadlineTask.deserialize(line);
            case "E" -> EventTask.deserialize(line);
            default -> throw new IllegalArgumentException("Unknown task type: " + type);
        };
    }

    @Override
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", isDone ? "X" : " ", description);
    }

}
