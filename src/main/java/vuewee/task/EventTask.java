package vuewee.task;

import java.util.regex.Pattern;

/**
 * Represents a task that has a start date and an end date.
 */
public class EventTask extends Task {
    private TaskLocalDate from;
    private TaskLocalDate to;

    private static final int EXPECTED_DELIMETED_PARAM_COUNT = 4;

    public EventTask() {
        super();
        this.type = TaskType.EVENT;
    }

    /**
     * Constructs an EventTask object with the specified description, start date,
     * and end date.
     *
     * @param description The description of the event task.
     * @param from        The start date of the event task.
     * @param to          The end date of the event task.
     */
    public EventTask(String description, TaskLocalDate from, TaskLocalDate to) {
        super(description, TaskType.EVENT);
        this.from = from;
        this.to = to;
    }

    /**
     * Serializes the EventTask object into a string representation.
     *
     * @return The serialized string representation of the EventTask object.
     */
    @Override
    String serialize() {
        return this.type.toChar() + Task.DELIMETER_SPACE + (this.isDone ? "1" : "0") + Task.DELIMETER_SPACE
                + this.description.replace(Task.DELIMETER, "\\" + Task.DELIMETER) + Task.DELIMETER_SPACE
                + this.from.serialize() + Task.DELIMETER_SPACE + this.to.serialize();
    }

    /**
     * Deserializes the specified string representation and populates the EventTask
     * object with the deserialized data.
     *
     * @param serializedTask The serialized string representation of the EventTask
     *                       object.
     * @throws IllegalArgumentException If the serialized task has an invalid
     *                                  format.
     */
    @Override
    void deserialize(String serializedTask) {
        String[] parts = serializedTask.split(Pattern.quote(DELIMETER_SPACE), EXPECTED_DELIMETED_PARAM_COUNT);

        if (parts.length != EXPECTED_DELIMETED_PARAM_COUNT) {
            throw new IllegalArgumentException("Invalid task format: " + serializedTask);
        }

    }

    @Override
    public String toString() {
        return super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }
}