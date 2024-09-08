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

    /**
     * Validate arguments for creating a new instance of Todo, and returns
     * a new instance if valid. Otherwise throws TaskValidationException.
     * @param args
     * @throws TaskValidationException
     */
    public static Todo validateThenCreate(String ...args) throws TaskValidationException {
        if (args.length != 1) {
            throw new TaskValidationException("Invalid number of arguments given for Todo!");
        }
        if (args[0].isBlank()) {
            throw new TaskValidationException("Description cannot be empty!");
        }
        return new Todo(args[0]);
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
