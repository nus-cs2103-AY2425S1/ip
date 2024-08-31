package tasks;

import exceptions.CorruptedTaskStringException;
import exceptions.EmptyArgumentException;

public abstract class Task {
    private boolean completed;
    private final String description;
    public Task(boolean completed, String description) {
        this.completed = completed;
        this.description = description;
    }
    public Task(String description) {
        this(false, description);
    }
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
    protected String getCompletedAndDescription() {
        return (completed ? "1" : "0") + " | " + description;
    }
    @Override
    public String toString() {
        return String.format("[%s] %s", completed ? "X" : " ", description);
    }

    public static Task fromDataString(String taskString) throws CorruptedTaskStringException, EmptyArgumentException {
        return switch (taskString.charAt(0)) {
        case 'E' -> Event.fromDataString(taskString);
        case 'D' -> Deadline.fromDataString(taskString);
        case 'T' -> Todo.fromDataString(taskString);
        default -> throw new CorruptedTaskStringException();
        };
    }

    public abstract String toDataString();
}