package astra.task;

import astra.AstraException;

import java.util.function.Function;

/**
 * Represents a task.
 */
public abstract class Task {
    /**
     * Represents the types of a task.
     */
    public enum TaskType {
        TODO,
        DEADLINE,
        EVENT,
    }

    private boolean done;
    private final String name;

    /**
     * Constructor for Task.
     *
     * @param name The name of the task.
     * @throws AstraException If the name is empty.
     */
    public Task(String name) throws AstraException {
        if (name.isBlank()) {
            throw new AstraException("Task description cannot be empty.");
        }
        this.name = name;
        this.done = false;
    }

    /**
     * Constructor for Task.
     *
     * @param done The status of the task.
     * @param name The name of the task.
     * @throws AstraException If the name is empty.
     */
    public Task(boolean done, String name) throws AstraException {
        if (name.isBlank()) {
            throw new AstraException("Task description cannot be empty.");
        }
        this.name = name;
        this.done = done;
    }

    /**
     * Converts a text representation of a task to a Task object.
     *
     * @param text The text representation of the task.
     * @return The Task object.
     */
    public static Task fromText(String text) throws AstraException {
        String[] data = text.split(" \\| ");
        Function<String, Boolean> toBool = x -> x.equals("1");

        switch (data[0]) {
        case "T":
            return new Todo(toBool.apply(data[1]), data[2]);
        case "D":
            return new Deadline(toBool.apply(data[1]), data[2], data[3]);
        case "E":
            return new Event(toBool.apply(data[1]), data[2], data[3], data[4]);
        default:
            throw new RuntimeException("Invalid file format.");
        }
    }

    /**
     * Converts the task to a text file format.
     *
     * @return The text file format of the task.
     */
    public String toText() {
        char status = done ? '1' : '0';
        return status + " | " + name;
    }

    @Override
    public String toString() {
        char status = done ? 'X' : ' ';
        return String.format("[%s] %s", status, name);
    }

    public void setDone(boolean status) {
        this.done = status;
    }
}
