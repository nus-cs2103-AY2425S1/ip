/**
 * Represents a Deadline task.
 * A Deadline task is a task with a description and a specific due date or time by which it should be completed.
 */
public class Deadline extends Task{

    /** The due date or time of the deadline. */
    protected String by;

    /**
     * Constructs a new Deadline task with the given description and due date.
     *
     * @param description the description of the Deadline task.
     * @param by the due date or time by which the task should be completed.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Creates a Deadline task from the given input string.
     * The input string must start with the command "deadline" followed by the task description and the due date.
     *
     * @param input the input string containing the task details.
     * @return the created Deadline task.
     * @throws InputException if the input format is incorrect or if the description is missing.
     */
    @Override
    public Task createTask(String input) throws InputException{
        if (input.equalsIgnoreCase("deadline")) {
            throw new InputException("To add a Deadline task, use the following format: deadline <task description> /by <date/time>");
        }
        String[] details = input.substring(9).split(" /by ");
        if (details.length == 2) {
            String description = details[0].trim();
            String by = details[1].trim();
            if (description.isEmpty()) {
                throw new InputException("You need to describe your Deadline!");
            }
            return new Deadline(description, by);
        } else {
            throw new InputException("Invalid format. Use: deadline <description> /by <date>");
        }
    }

    /**
     * Returns a string representation of the Deadline task.
     * The string includes a "[D]" prefix to indicate that it is a Deadline task, followed by the task status, description, and due date.
     *
     * @return a string representing the Deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
