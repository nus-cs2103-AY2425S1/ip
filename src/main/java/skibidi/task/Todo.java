package skibidi.task;

/** Task subclass with only description. */
public class Todo extends AbstractTask {
    /** Construct new Todo instance using command inputs. */
    public Todo(String description) {
        super(description);
    }

    /** Construct new Todo instance using deserialized inputs. */
    public Todo(String marker, String description) {
        super(marker, description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String serialize() {
        return String.join("|", new String[]{"T", getStatusIcon(), description});
    }
}
