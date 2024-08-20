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
        return (isDone ? "X" : " "); // mark done task with X
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

    public static boolean isTaskCommand(String input) {
        String[] parts = input.split(" ");
        return TASKS.contains(parts[0]);
    }

    public static Task initialise(String input) {
        String[] parts = input.split(" ");
        switch (parts[0]) {
            case "todo":
                return ToDos.createTask(input);
            case "event":
                return Events.createTask(input);
            case "deadline":
                return Deadlines.createTask(input);
        }
        return null;
    }
}
