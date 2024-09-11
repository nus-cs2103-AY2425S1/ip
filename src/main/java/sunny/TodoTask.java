package sunny;
/**
 * Represent to do tasks
 */
public class TodoTask extends Task {
    /**
     * Creates a to do task
     * @param description of the task
     * @param isDone true if task is completed, false otherwise
     */
    public TodoTask(String description, boolean isDone) {
        super(description, isDone);
    }
    /**
     * Creates a to do task, automatically set to not done
     * @param description of the task
     */
    public TodoTask(String description) {
        super(description);
    }

    @Override
    public String toString() {
        if (isDone) {
            return String.format("[T][X] %s", description);
        } else {
            return String.format("[T][] %s", description);
        }
    }
}
