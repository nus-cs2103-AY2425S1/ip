package kat.tasks.impl;

/**
 * Represents a simple todo task without a specific time or date.
 */
public class TodoTask extends AbstractTask {

    /**
     * Constructs a new TodoTask with the given description.
     *
     * @param description The description of the task.
     */
    public TodoTask(String description) {
        super(description);
    }

    /**
     * Deserializes a TodoTask from a string representation.
     *
     * @param line The string representation of the TodoTask.
     * @return The deserialized TodoTask object.
     * @throws IllegalArgumentException if the task format is invalid.
     */
    public static TodoTask deserialize(String line) {
        String[] parts = line.split(" \\| ");

        if (parts.length != 3 || !parts[0].equals("T")) {
            throw new IllegalArgumentException("Invalid TodoTask format");
        }

        TodoTask todoTask = new TodoTask(parts[2]);
        todoTask.setDone(parts[1].equals("1"));

        return todoTask;
    }

    @Override
    public String serialize() {
        return String.format("T | %d | %s", isDone ? 1 : 0, description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
