/**
 * Represents a to-do task with a description.
 * This class extends the Task class and provides specific functionality
 * for to-do tasks.
 */
public class ToDosTask extends Task {

    /**
     * Constructs a ToDosTask with the specified description.
     *
     * @param description The description of the to-do task.
     */
    public ToDosTask(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the to-do task, including its
     * status (done or not) and description.
     *
     * @return A formatted string representing the to-do task.
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
