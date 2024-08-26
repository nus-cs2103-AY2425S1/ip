/**
 * Represents a Deadline task with a specific deadline.
 */
public class Deadline extends Task {
    private String deadline;

    /**
     * Constructs a Deadline task with a given description.
     *
     * @param description The description of the Deadline task.
     * @throws EmptyInputException if the description is empty.
     */
    public Deadline(String description) throws EmptyInputException {
        super(description);
        int slashIndex = description.indexOf("/");

        this.name = description.substring(0, slashIndex).trim();

        // Extract the substring after the slash and trim it
        String temp = description.substring(slashIndex + 1).trim();

        // Reformat the deadline to the desired format
        deadline = "(by: " + temp.substring(3).trim() + ")";
    }

    /**
     * Returns a string representation of the Deadline task, including its deadline.
     *
     * @return The string representation of the Deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + "[" + this.getStatusIcon() + "] " + this.name + " " + deadline;
    }
}
