/**
 * Represents a deadline task.
 */
public class Deadline extends Task {
    private final String deadline;

    /**
     * Constructor for Deadline class.
     *
     * @param description Description of the task.
     * @param deadline    Deadline of the task.
     */
    public Deadline(String description, String deadline) {
        super(TaskType.DEADLINE, description);
        this.deadline = deadline;
    }

    /**
     * Serializes the deadline to a string.
     * The string is in the format of "D | 1 | description | deadline".
     * D is the task type, 1 is 1 if the task is done, description is the description of the task,
     * deadline is the deadline of the task.
     * The task type, is done status, description and deadline are separated by " | ".
     * The task type is represented by the first character of the task type.
     * For example, if the task type is Deadline, the task type is D.
     * If the task is done, the second character is 1, otherwise it is 0.
     * The description and deadline are the description and deadline of the task respectively.
     * The task type, is done status, description and deadline are separated by " | ".
     * The description and deadline are separated by " | ".
     *
     * @return Serialized deadline.
     */
    @Override
    public String serialize() {
        return super.serialize() + " | " + deadline;
    }

    /**
     * Returns the string representation of the deadline.
     *
     * @return String representation of the deadline.
     */
    @Override
    public String toString() {
        return super.toString() + " (by: " + deadline + ")";
    }
}
