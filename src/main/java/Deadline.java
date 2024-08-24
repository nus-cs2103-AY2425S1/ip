public class Deadline extends Task {
    /**
     * Constructs a Deadline task with the specified description and deadline
     *
     * @param description The description of the task provided by the user
     * @param deadline The deadline of the task provided by the user
     */
    private String deadline;
    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    /**
     * Returns a string representation of the Deadline object.
     * The string includes a "[D]" prefix to indicate that this is a Deadline task,
     * followed by the string representation provided by the Task superclass.
     *
     * @return A string in the format: "[D] <super.toString()>"
     */
    @Override
    public String toString() {
        return "[D] " + super.toString() + "(by: " + this.deadline + ")";
    }
}
