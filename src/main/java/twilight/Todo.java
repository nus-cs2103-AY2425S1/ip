package twilight;

/**
 * Holds information for todo task.
 */
public class Todo extends Task {

    /**
     * Creates instance.
     */
    public Todo(String description) {
        super(description);
    }


    public Todo(boolean status, String description) {
        super(description, status);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toStorage() {
        return "T," + super.toStorage();
    }
}
