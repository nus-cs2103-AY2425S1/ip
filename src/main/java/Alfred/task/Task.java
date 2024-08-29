package alfred.task;

import alfred.exception.AlfredException;

import java.util.Arrays;
import java.util.List;

abstract public class Task {
    protected String description;
    protected boolean isDone;

    private static final List<String> TASKS = Arrays.asList("todo", "deadline", "event");

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done Alfred.task with X
    }

    public void markAsDone() {
        isDone = true;
    }

    public void unmark() {
        isDone = false;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    public static boolean isCreateTaskCommand(String input) {
        String[] parts = input.split(" ");
        return TASKS.contains(parts[0]);
    }

    public static Task initialise(String input) throws AlfredException {
        String[] parts = input.split(" ");
        switch (parts[0]) {
        case "todo":
            return ToDos.createTask(input);
        case "event":
            return Events.createTask(input);
        case "deadline":
            return Deadlines.createTask(input);
        default:
            throw new AlfredException("Invalid task: " + parts[0]);
        }
    }

    public abstract String toFileFormat();

    public static Task fromFileFormat(String fileFormat) throws AlfredException {
        String[] parts = fileFormat.split("\\|");
        if (parts.length < 3) {
            throw new AlfredException("Corrupted save: Invalid task format");
        }

        // Trim parts
        for (int i = 0; i < parts.length; i++) {
            parts[i] = parts[i].trim();
        }

        String type = parts[0];
        boolean isDone = parts[1].equals("X");
        String description = parts[2];

        switch (type) {
        case "T":
            return new ToDos(description, isDone);
        case "D":
            if (parts.length != 4) {
                throw new AlfredException("Corrupted save: Invalid deadline format");
            }
            String deadline = parts[3];
            return new Deadlines(description, deadline, isDone);
        case "E":
            if (parts.length != 5) {
                throw new AlfredException("Corrupted save: Invalid event format");
            }
            String from = parts[3];
            String to = parts[4];
            return new Events(description, from, to, isDone);
        default:
            throw new AlfredException("Corrupted save: Unknown task type");
        }
    }
}
