package oliver;

/**
 * Represents a task that has to be done, but with no deadline
 */
public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns the formatted string to be stored in the data file.
     *
     * @return formatted string representing the todo task
     */
    @Override
    public String getFormatted() {
        return "T|" + super.getStatusIcon() + "|" + super.description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
