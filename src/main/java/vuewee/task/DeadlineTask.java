package vuewee.task;

import java.util.regex.Pattern;

/**
 * Represents a task with a deadline.
 */
public class DeadlineTask extends Task {
    private static final int EXPECTED_DELIMETED_PARAM_COUNT = 3;

    private TaskLocalDate byDate;

    DeadlineTask() {
        super();
        this.type = TaskType.DEADLINE;
    }

    /**
     * Constructor for DeadlineTask. Sets the task description and deadline.
     *
     * @param description The description of the task.
     * @param by          The deadline of the task.
     */
    public DeadlineTask(String description, TaskLocalDate by) {
        super(description, TaskType.DEADLINE);
        this.byDate = by;
    }

    /**
     * Serializes the DeadlineTask object into a string representation.
     *
     * @return The serialized string representation of the DeadlineTask object.
     */
    @Override
    String serialize() {
        return this.type.toChar() + Task.DELIMETER_SPACE + (this.isDone ? "1" : "0") + Task.DELIMETER_SPACE
                + this.description.replace(Task.DELIMETER, "\\" + Task.DELIMETER) + Task.DELIMETER_SPACE + this.byDate
                        .serialize();
    }

    /**
     * Deserializes the string representation of a DeadlineTask object.
     *
     * @param serializedTask The serialized string representation of the
     *                       DeadlineTask object.
     * @throws IllegalArgumentException If the serialized task has an invalid
     *                                  format.
     */
    @Override
    void deserialize(String serializedTask) {
        String[] parts = serializedTask.split(Pattern.quote(DELIMETER_SPACE), EXPECTED_DELIMETED_PARAM_COUNT);

        if (parts.length != EXPECTED_DELIMETED_PARAM_COUNT) {
            throw new IllegalArgumentException("Invalid task format: " + serializedTask);
        }

        boolean isDone = parts[0].equals("1");
        String description = parts[1].replace("\\" + DELIMETER, DELIMETER);

        this.description = description;
        this.isDone = isDone;
        this.byDate = TaskLocalDate.deserialize(parts[2]);
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + this.byDate.toString() + ")";
    }
}
