package seedu.maxine.task;

public class Deadline extends Task {

    protected String deadline;

    public Deadline() {
        super();
    }


    /**
     * Constructs a {@code Deadline} object with the specified description and deadline.
     * <p>
     * This constructor initializes a new {@code Deadline} task with the given description 
     * and attempts to parse the deadline using a date-time parser. If the parsing fails, 
     * the deadline is stored as-is.
     * </p>
     *
     * @param description A description of the deadline task. This description is used to 
     *                    define what the task is about.
     * @param deadline The deadline for the task, which is expected to be in a date-time format.
     *                 This value is parsed to set the deadline, or stored as a string if parsing 
     *                 fails.
     */
    public Deadline(String description, String deadline) {
        super(description);
        try {
            this.deadline = dateTimeParser(deadline.trim());
        } catch (Exception e) {
            this.deadline = deadline;
        }
    }


    /**
     * Returns a string representation of the deadline task in a human-readable format.
     * <p>
     * This format includes the task type indicator, its status icon, the description, and 
     * the deadline in a readable format suitable for user output.
     * </p>
     *
     * @return A string representation of the deadline task formatted as "[D][status] description (by: deadline)".
     */
    @Override
    public String toString() {
        return "[D]" + getStatusIcon() + description + " (by: " + deadline + ")";
    }


    /**
     * Returns a string representation of the deadline task suitable for file storage.
     * <p>
     * This format includes a type indicator, status, and the deadline, formatted for easy 
     * parsing and writing to a file. It uses a space separator between the status and 
     * deadline.
     * </p>
     *
     * @return A string representation of the deadline task formatted as "D[status] / deadline" for file storage.
     */
    @Override
    public String writeToFile() {
        return "D" + super.writeToFile() + " / " + deadline;
    }

}

