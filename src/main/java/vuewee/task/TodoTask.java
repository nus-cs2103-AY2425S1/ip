package vuewee.task;

import java.util.regex.Pattern;

/**
 * Represents a todo task with a description.
 */
public class TodoTask extends Task {
    private static final int EXPECTED_DELIMETED_PARAM_COUNT = 2;

    TodoTask() {
        super();
        this.type = TaskType.TODO;
    }

    /**
     * Constructor for TodoTask with description.
     *
     * @param description The description of the todo task.
     */
    public TodoTask(String description) {
        super(description, TaskType.TODO);
    }

    /**
     * Constructor for TodoTask with description and marked status.
     *
     * @param description The description of the todo task.
     * @param isDone      The marked status of the todo task.
     */
    public TodoTask(String description, boolean isDone) {
        super(description, TaskType.TODO, isDone);
    }

    /**
     * Deserializes the serialized task string and sets the task properties. Used in
     * task storage.
     *
     * @param serializedTask The serialized task string.
     * @throws IllegalArgumentException If the serialized task string has an invalid
     *                                  format.
     */
    @Override
    void deserialize(String serializedTask) {
        assert serializedTask != null : "Serialized task cannot be null";

        String[] parts = serializedTask.split(Pattern.quote(DELIMETER_SPACE), EXPECTED_DELIMETED_PARAM_COUNT);

        if (parts.length != EXPECTED_DELIMETED_PARAM_COUNT) {
            throw new IllegalArgumentException("Invalid task format: " + serializedTask);
        }

        boolean isDone = parts[0].equals("1");
        String description = parts[1].replace("\\" + DELIMETER, DELIMETER);

        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Serializes the task to a string representation for task storage.
     *
     * @return The serialized task string.
     */
    @Override
    String serialize() {
        return this.type.toChar() + Task.DELIMETER_SPACE + (this.isDone ? "1" : "0") + Task.DELIMETER_SPACE
                + this.description.replace(Task.DELIMETER, "\\" + Task.DELIMETER);
    }

}
