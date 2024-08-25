public class Deadline extends Task {
    private String due;

    /**
     * Constructor for Deadline.
     * @param description a String describing the Deadline
     * @param due a String describing the due date/time
     */
    public Deadline(String description, String due) {
        super(description.strip());
        StringBuilder str = new StringBuilder(due.strip());
        str.insert(str.indexOf(" "), ':');
        this.due = str.toString();
    }

    /**
     * Returns a formatted String that represents the Deadline object and its fields
     * @return a String representation of the Deadline object
     */
    @Override
    public String toString() {
        return String.format("[D]%s (%s)", super.toString(), due);
    }
}
