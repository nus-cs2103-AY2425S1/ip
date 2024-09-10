package assistinator;

/**
 * Represents todo task
 */
public class Todo extends Task {

    /**
     * Initialise task
     * @param description Task description
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Convert task to formatted string
     * @return formatted string
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Generate string to print onto file
     * @return String for file
     */
    public String toFileString() {
        return String.format("T | %s | %s", isDone ? TaskStatus.DONE : TaskStatus.NOTDONE, description);
    }
}
