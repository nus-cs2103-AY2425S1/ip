package bellroy.task;

/**
 * Encapsulates a Todo Task
 */
public class Todo extends Task {

    public Todo(String description) {
        super("T", description);
    }

    @Override
    public String toString() {
        String message = "[" + this.type + "]" + "[" + this.getStatusIcon() + "] " + this.description;

        return message;
    }
}
