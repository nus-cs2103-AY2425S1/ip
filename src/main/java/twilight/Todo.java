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

    public Todo(boolean isDone, String description, String tag) {
        super(description, isDone, tag);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString() + this.getTagString();
    }

    @Override
    public String toStorageString() {
        return "T," + super.toStorageString() + "," + this.tag;
    }
}
