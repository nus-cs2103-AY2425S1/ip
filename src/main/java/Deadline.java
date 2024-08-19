/**
 * Represents a Deadline task with a description and a due date.
 */
public class Deadline extends Task {
    private String deadline;

    /**
     * Constructs a Deadline task with the specified description and due date.
     *
     * @param description The description of the Deadline task.
     * @param deadline The due date of the Deadline task.
     */
    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    /**
     * Validates the description and due date of the Deadline task.
     *
     * @param command The command string used to create the task.
     * @throws OllieException if the description or due date is invalid.
     */
    @Override
    public void validateDescription(String command) throws OllieException {
        String[] parts = command.split(" /by ");
        if (parts.length != 2) {
            throw new OllieException("Please enter a name and deadline for the task! ☺");
        }
        if (parts[0].trim().isEmpty()) {
            throw new EmptyDescriptionException("deadline");
        }
        if (parts[1].trim().isEmpty()) {
            throw new OllieException("Please enter a deadline for the task! ☺");
        }
    }

    /**
     * Creates a Deadline task from the command string.
     *
     * @param command The command string containing the task description and due date.
     * @return The created Deadline task.
     * @throws OllieException if the description or due date is invalid.
     */
    public static Deadline createTask(String command) throws OllieException {
        String[] parts = command.substring(8).split(" /by ");
        if (parts.length != 2) {
            throw new OllieException("Please enter a name and deadline for the task! ☺");
        }
        return new Deadline(parts[0], parts[1]);
    }

    @Override
    public String toString() {
        return "[Deadline]" + super.toString() + " (by: " + deadline + ")";
    }
}