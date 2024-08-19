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
        super(description, TaskType.DEADLINE);
        this.deadline = deadline;
    }

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

    public static Deadline createTask(String command) throws OllieException {
        String[] parts = command.substring(8).split(" /by:");
        if (parts.length != 2) {
            throw new OllieException("Please enter in the format:\n" + "deadline task_name /by: due_date");
        }
        return new Deadline(parts[0].trim(), parts[1].trim());
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + deadline + ")";
    }
}